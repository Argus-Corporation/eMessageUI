package net.argus.emessage.api.ui;

import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class Path extends Path2D.Float {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7963807089346955563L;

	public void arcTo(float rx, float ry, float theta, boolean largeArcFlag, boolean sweepFlag, float x, float y) {
        // Ensure radii are valid
        if (rx == 0 || ry == 0) {
                lineTo(x, y);
                return;
        }
        // Get the current (x, y) coordinates of the path
        Point2D p2d = getCurrentPoint();
        float x0 = (float) p2d.getX();
        float y0 = (float) p2d.getY();
        // Compute the half distance between the current and the final point
        float dx2 = (x0 - x) / 2.0f;
        float dy2 = (y0 - y) / 2.0f;
        // Convert theta from degrees to radians
        theta = (float) Math.toRadians(theta % 360f);

        //
        // Step 1 : Compute (x1, y1)
        //
        float x1 = (float) (Math.cos(theta) * (double) dx2 + Math.sin(theta)
                        * (double) dy2);
        float y1 = (float) (-Math.sin(theta) * (double) dx2 + Math.cos(theta)
                        * (double) dy2);
        // Ensure radii are large enough
        rx = Math.abs(rx);
        ry = Math.abs(ry);
        float Prx = rx * rx;
        float Pry = ry * ry;
        float Px1 = x1 * x1;
        float Py1 = y1 * y1;
        double d = Px1 / Prx + Py1 / Pry;
        if (d > 1) {
                rx = Math.abs((float) (Math.sqrt(d) * (double) rx));
                ry = Math.abs((float) (Math.sqrt(d) * (double) ry));
                Prx = rx * rx;
                Pry = ry * ry;
        }

        //
        // Step 2 : Compute (cx1, cy1)
        //
        double sign = (largeArcFlag == sweepFlag) ? -1d : 1d;
        float coef = (float) (sign * Math
                        .sqrt(((Prx * Pry) - (Prx * Py1) - (Pry * Px1))
                                        / ((Prx * Py1) + (Pry * Px1))));
        float cx1 = coef * ((rx * y1) / ry);
        float cy1 = coef * -((ry * x1) / rx);

        //
        // Step 3 : Compute (cx, cy) from (cx1, cy1)
        //
        float sx2 = (x0 + x) / 2.0f;
        float sy2 = (y0 + y) / 2.0f;
        float cx = sx2
                        + (float) (Math.cos(theta) * (double) cx1 - Math.sin(theta)
                                        * (double) cy1);
        float cy = sy2
                        + (float) (Math.sin(theta) * (double) cx1 + Math.cos(theta)
                                        * (double) cy1);

        //
        // Step 4 : Compute the angleStart (theta1) and the angleExtent (dtheta)
        //
        float ux = (x1 - cx1) / rx;
        float uy = (y1 - cy1) / ry;
        float vx = (-x1 - cx1) / rx;
        float vy = (-y1 - cy1) / ry;
        float p, n;
        // Compute the angle start
        n = (float) Math.sqrt((ux * ux) + (uy * uy));
        p = ux; // (1 * ux) + (0 * uy)
        sign = (uy < 0) ? -1d : 1d;
        float angleStart = (float) Math.toDegrees(sign * Math.acos(p / n));
        // Compute the angle extent
        n = (float) Math.sqrt((ux * ux + uy * uy) * (vx * vx + vy * vy));
        p = ux * vx + uy * vy;
        sign = (ux * vy - uy * vx < 0) ? -1d : 1d;
        float angleExtent = (float) Math.toDegrees(sign * Math.acos(p / n));
        if (!sweepFlag && angleExtent > 0) {
                angleExtent -= 360f;
        } else if (sweepFlag && angleExtent < 0) {
                angleExtent += 360f;
        }
        angleExtent %= 360f;
        angleStart %= 360f;

        Arc2D.Float arc = new Arc2D.Float();
        arc.x = cx - rx;
        arc.y = cy - ry;
        arc.width = rx * 2.0f;
        arc.height = ry * 2.0f;
        arc.start = -angleStart;
        arc.extent = -angleExtent;
        append(arc, true);
	}

}
