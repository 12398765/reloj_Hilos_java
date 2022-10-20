package proyecto_reloj;

import java.awt.*;
import java.util.Calendar;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Proyecto_Reloj extends JFrame implements Runnable {
    JPanel panel = new JPanel();
    int hora, min, min2, seg;
    private static final double rad = (double) (Math.PI / 30.0);
    private Image buffer;
    private Thread hilo;
    int manHora = 50, manMin = 70, manSec = 100;
  
     
    public Proyecto_Reloj() {
        Color b=new Color(161, 164, 183);
        this.setBounds(100, 100, 720, 700);
        this.setLocationRelativeTo(null);
        setBackground(b);
        setTitle("Diseño Reloj");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel.setBackground(Color.gray);
        add(panel);
        setVisible(true);
        hilo = new Thread((Runnable) this);
        hilo.start();

    }
    
    @Override
    public void update(Graphics g) {
        double xSec, ySec, anS;
        double xMin, yMin, anM = 0;
        double xHora, yHora, anH = 0;
        Color color4 = new Color(228, 193, 181);

        ////////////////////////////////////////////////////////////////////////////
        g.setClip(270, 300, 300, 350);
        Calendar cal = Calendar.getInstance();
        buffer = createImage(180, 190);
        Graphics gbuf = buffer.getGraphics();
        if (cal.get(Calendar.MINUTE) != min) {
            seg = cal.get(Calendar.SECOND);
            min = cal.get(Calendar.MINUTE);
            min += 5;        //para que siempre se cumpla la condicion del if
            hora = cal.get(Calendar.HOUR);
            min2 = min - 5;

            System.out.println("Hora: " + hora + " : " + min2 + " : " + seg);

            //Segundos
            if (seg == 0) {
                anS = 180;
            }
            anS = 180;

            for (int i = 0; i < seg; i++) {
                anS -= 6;
            }

            xSec = getX(anS, manSec);
            ySec = getY(anS, manSec);
            gbuf.setColor(color4);
            gbuf.fillRect(0, 0, 300, 300);

            gbuf.setColor(Color.red);
            gbuf.drawLine(90, 95, 90 + (int) xSec, 95 + (int) ySec);
            buffer.getGraphics();

            g.drawImage(buffer, 270, 300, this);

            //Minutos
            anM = 210;
            for (int i = 0; i < min; i++) {
                anM -= 6;
            }

            xMin = getX(anM, manMin);
            yMin = getY(anM, manMin);
            gbuf.setColor(Color.blue);
            gbuf.drawLine(90, 95, 90 + (int) xMin, 95 + (int) yMin);
            g.drawImage(buffer, 270, 300, this);

            //Horas
            anH = 180;
            for (int i = 0; i < hora; i++) {
                anH -= 30;
            }

            xHora = getX(anH, manHora);
            yHora = getY(anH, manHora);
            gbuf.setColor(Color.BLACK);
            gbuf.drawLine(90, 95, 90 + (int) xHora, 95 + (int) yHora);
            g.drawImage(buffer, 270, 300, this);
        }

    }

    public void paint(Graphics g) {
        Color cg = new Color(177, 154, 156);
        Color c_claro = new Color(66, 10, 15);
        Color c_dark = new Color(33, 2, 5);
        Color color4 = new Color(228, 193, 181);
        Color c5 = new Color(86, 29, 10);
        Color ama = new Color(243, 234, 119);
        Color c3 = new Color(242, 207, 196);
        Color c2 = new Color(185, 140, 101);

        //Decoración superior centro
        int[] decrelx = {345, 370, 380, 335};
        int[] decrely = {203, 203, 195, 195};

        int[] decrelx2 = {352, 357, 362};
        int[] decrely2 = {195, 180, 195};

        //decoracion izquierda
        int[] u1x = {90, 90, 100, 100};
        int[] u1y = {180, 200, 200, 190};
        int[] u2x = {140, 140, 130, 130};
        int[] u2y = {180, 200, 200, 190};
        int[] u3x = {90, 100, 130, 140};
        int[] u3y = {180, 190, 190, 180};

        //Decoracion cupula derecha
        int[] u1dx = {570, 570, 580, 580};
        int[] u1dy = {180, 200, 200, 190};
        int[] u2dx = {620, 620, 610, 610};
        int[] u2dy = {180, 200, 200, 190};
        int[] u3dx = {570, 580, 610, 620};
        int[] u3dy = {180, 190, 190, 180};

        //centro
        g.setColor(cg);
        g.fillPolygon(decrelx, decrely, 4);
        g.setColor(c_dark);
        g.fillPolygon(decrelx2, decrely2, 3);

        g.setColor(Color.YELLOW);
        //      g.fillArc(109,140, 50, 50, 90, -180);

        g.fillOval(104, 125, 25, 50);
        g.fillOval(584, 125, 25, 50);

        //fondo
        g.setColor(ama);
        g.fillPolygon(u1x, u1y, 4);
        g.fillPolygon(u2x, u2y, 4);
        g.fillPolygon(u3x, u3y, 4);
        g.fillPolygon(u1dx, u1dy, 4);
        g.fillPolygon(u2dx, u2dy, 4);
        g.fillPolygon(u3dx, u3dy, 4);

        g.setColor(Color.black);
        g.drawPolygon(decrelx, decrely, 4);  ////////////////////////////////7
        g.drawRect(100, 190, 30, 15);
        g.drawOval(104, 125, 25, 50);
        g.drawOval(584, 125, 25, 50);

        g.drawPolygon(u1x, u1y, 4);
        g.drawPolygon(u2x, u2y, 4);
        g.drawPolygon(u3x, u3y, 4);

        //derecha
        g.drawPolygon(u1dx, u1dy, 4);
        g.drawPolygon(u2dx, u2dy, 4);
        g.drawPolygon(u3dx, u3dy, 4);
        g.setColor(Color.black);
        g.fillRect(112, 170, 10, 10);
        g.fillRect(592, 170, 10, 10);
        g.drawRect(112, 170, 10, 10);
        g.drawRect(592, 170, 10, 10);

        //el largo en x entre ambos pilares es 530
        int dsx = 90, dsy = 203;
        for (int i = 0; i < 10; i++) {
            if (i == 0 || i == 2 || i == 4 || i == 6 || i == 8 || i == 10) {
                g.setColor(ama);
                g.fillRect(dsx, dsy, 530, 2);
            } else {
                g.setColor(c_claro);
                g.fillRect(dsx, dsy, 530, 2);
            }
            g.setColor(Color.black);
            g.drawRect(dsx, dsy, 530, 2);

            dsy += 2;
        }

        //Pilar derecho
        g.setColor(Color.YELLOW);
        int[] pilxd = {550, 570, 570};
        int[] pilyd = {630, 635, 650};
        int[] pixd = {621, 621, 641};
        int[] piyd = {650, 635, 630};
        int[] pxd = {550, 550, 570, 570};
        int[] pyd = {629, 614, 619, 634};
        int[] px2d = {621, 621, 640, 640};
        int[] py2d = {634, 619, 614, 629};
        int[] px3d = {550, 570, 620, 640};
        int[] py3d = {614, 619, 619, 614};
        int[] px4d = {550, 550, 640, 640};
        int[] py4d = {613, 608, 608, 613};
        int[] px5d = {550, 550, 570, 570};
        int[] py5d = {608, 600, 595, 608};
        int[] px6d = {640, 620, 620, 640};
        int[] py6d = {608, 608, 595, 600};
        int[] px7d = {570, 570, 585, 605, 620, 620};
        int[] py7d = {595, 580, 575, 575, 580, 595};
        int[] px8d = {550, 570, 570};
        int[] py8d = {600, 595, 580};
        int[] px9d = {620, 620, 640};
        int[] py9d = {580, 595, 600};
        int[] pxxd = {595, 595, 610, 610, 605};
        int[] pyyd = {570, 565, 550, 565, 570};
        int[] pxx1d = {595, 595, 580, 580, 585};
        int[] pyy1d = {570, 565, 550, 565, 570};
        int[] pxx2d = {580, 610, 595};
        int[] pyy2d = {550, 550, 565};
        int[] pxx3d = {570, 580, 610, 620};
        int[] pyy3d = {545, 550, 550, 545};

        g.fillPolygon(pilxd, pilyd, 3);
        g.fillRect(570, 635, 50, 15);
        g.fillPolygon(pilxd, pilyd, 3);
        g.fillPolygon(pixd, piyd, 3);
        g.fillPolygon(pxd, pyd, 4);
        g.fillPolygon(px2d, py2d, 4);
        g.fillRect(570, 620, 50, 15);
        g.fillPolygon(px4d, py4d, 4);
        g.fillPolygon(px5d, py5d, 4);
        g.fillPolygon(px6d, py6d, 4);
        g.fillPolygon(px7d, py7d, 6);
        g.fillRect(570, 595, 50, 12);
        g.fillPolygon(px8d, py8d, 3);
        g.fillPolygon(px9d, py9d, 3);
        g.fillPolygon(pxxd, pyyd, 5);
        g.fillPolygon(pxx1d, pyy1d, 5);
        g.fillPolygon(pxx3d, pyy3d, 4);

        //pilar rect
        int nx1 = 570, ny1 = 200;
        for (int i = 0; i < 10; i++) {
            if (i == 0 || i == 2 || i == 4 || i == 6 || i == 8 || i == 10) {
                g.setColor(ama);
                g.fillRect(nx1, ny1, 5, 345);
            } else {
                g.setColor(c_claro);
                g.fillRect(nx1, ny1, 5, 345);
            }
            g.setColor(Color.black);
            g.drawRect(nx1, ny1, 5, 345);

            nx1 += 5;
        }

        g.setColor(c_dark);
        g.fillPolygon(px3d, py3d, 4);

        g.setColor(cg);
        g.fillRect(585, 570, 20, 5);
        g.fillPolygon(pxx2d, pyy2d, 3);

        g.setColor(Color.black);
        g.drawPolygon(pilxd, pilyd, 3);
        g.drawRect(570, 635, 50, 15);
        g.drawPolygon(pixd, piyd, 3);
        g.drawPolygon(pxd, pyd, 4);
        g.drawPolygon(px2d, py2d, 4);
        g.drawPolygon(px3d, py3d, 4);
        g.drawRect(570, 620, 50, 15);
        g.drawPolygon(px4d, py4d, 4);
        g.drawPolygon(px5d, py5d, 4);
        g.drawPolygon(px6d, py6d, 4);
        g.drawPolygon(px7d, py7d, 6);
        g.drawRect(570, 595, 50, 12);
        g.drawPolygon(px8d, py8d, 3);
        g.drawPolygon(px9d, py9d, 3);
        g.drawPolygon(pxxd, pyyd, 5);
        g.drawPolygon(pxx1d, pyy1d, 5);
        g.drawPolygon(pxx2d, pyy2d, 3);
        g.drawPolygon(pxx3d, pyy3d, 4);

        //pilar izq
        g.setColor(Color.YELLOW);
        int[] pilx = {70, 90, 90};
        int[] pily = {630, 635, 650};

        int[] pix = {141, 141, 161};
        int[] piy = {650, 635, 630};

        int[] px = {70, 70, 90, 90};
        int[] py = {629, 614, 619, 634};

        int[] px2 = {141, 141, 160, 160};
        int[] py2 = {634, 619, 614, 629};

        int[] px3 = {70, 90, 140, 160};
        int[] py3 = {614, 619, 619, 614};

        int[] px4 = {70, 70, 160, 160};
        int[] py4 = {613, 608, 608, 613};

        int[] px5 = {70, 70, 90, 90};
        int[] py5 = {608, 600, 595, 608};

        int[] px6 = {160, 140, 140, 160};
        int[] py6 = {608, 608, 595, 600};

        int[] px7 = {90, 90, 105, 125, 140, 140};
        int[] py7 = {595, 580, 575, 575, 580, 595};

        int[] px8 = {70, 90, 90};
        int[] py8 = {600, 595, 580};

        int[] px9 = {140, 140, 160};
        int[] py9 = {580, 595, 600};

        int[] pxx = {115, 115, 130, 130, 125};
        int[] pyy = {570, 565, 550, 565, 570};

        int[] pxx1 = {115, 115, 100, 100, 105};
        int[] pyy1 = {570, 565, 550, 565, 570};

        int[] pxx2 = {100, 130, 115};
        int[] pyy2 = {550, 550, 565};

        int[] pxx3 = {90, 100, 130, 140};
        int[] pyy3 = {545, 550, 550, 545};

        g.fillPolygon(pilx, pily, 3);
        g.fillRect(90, 635, 50, 15);
        g.fillPolygon(pix, piy, 3);
        g.fillPolygon(px, py, 4);
        g.fillPolygon(px2, py2, 4);
        g.fillRect(90, 620, 50, 15);
        g.fillPolygon(px4, py4, 4);
        g.fillPolygon(px5, py5, 4);
        g.fillPolygon(px6, py6, 4);
        g.fillRect(90, 595, 50, 12);
        g.fillPolygon(px7, py7, 6);
        g.fillPolygon(px8, py8, 3);
        g.fillPolygon(px9, py9, 3);
        g.fillPolygon(pxx, pyy, 5);
        g.fillPolygon(pxx1, pyy1, 5);
        g.fillPolygon(pxx3, pyy3, 4);

        //pilar rect
        int nx = 90, ny = 200;
        for (int i = 0; i < 10; i++) {
            if (i == 0 || i == 2 || i == 4 || i == 6 || i == 8 || i == 10) {
                g.setColor(ama);
                g.fillRect(nx, ny, 5, 345);
            } else {
                g.setColor(c_claro);
                g.fillRect(nx, ny, 5, 345);
            }
            g.setColor(Color.black);
            g.drawRect(nx, ny, 5, 345);

            nx += 5;
        }

        g.setColor(c_dark);
        g.fillPolygon(px3, py3, 4);

        g.setColor(cg);
        g.fillRect(105, 570, 20, 5);
        g.fillPolygon(pxx2, pyy2, 3);

        g.setColor(Color.black);
        g.drawPolygon(pilx, pily, 3);
        g.drawRect(90, 635, 50, 15);
        g.drawPolygon(pix, piy, 3);
        g.drawPolygon(px, py, 4);
        g.drawPolygon(px2, py2, 4);
        g.drawRect(90, 620, 50, 15);
        g.drawPolygon(px3, py3, 4);
        g.drawPolygon(px4, py4, 4);
        g.drawPolygon(px5, py5, 4);
        g.drawPolygon(px6, py6, 4);
        g.drawRect(90, 595, 50, 12);
        g.drawPolygon(px7, py7, 6);
        g.drawPolygon(px8, py8, 3);
        g.drawPolygon(px9, py9, 3);
        g.drawRect(105, 570, 20, 5);
        g.drawPolygon(pxx, pyy, 5);
        g.drawPolygon(pxx1, pyy1, 5);
        g.drawPolygon(pxx2, pyy2, 3);
        g.drawPolygon(pxx3, pyy3, 4);
        
         g.setColor(Color.black);
        g.fillArc(180, 638, 55, 28, 90, 90);
        g.fillArc(480, 638, 55, 29, 0, 90);

        g.setColor(c_dark);
        g.fillRect(200, 638, 310, 20);

        g.fillRoundRect(60, 650, 600, 18, 10, 30);

        g.setColor(Color.black);
        g.drawRoundRect(60, 650, 600, 18, 10, 90);

        g.setColor(c_dark);
        g.fillRoundRect(50, 660, 100, 20, 10, 105);

        g.setColor(cg);
        g.fillRect(145, 660, 400, 8);
        g.setColor(Color.black);
        g.fillRect(145, 672, 415, 8);

        g.setColor(Color.white);
        g.fillRect(144, 663, 415, 14);

        g.setColor(Color.black);
        Font fuente = new Font("Franklin Gothic Heavy", Font.PLAIN, 10);
        g.setFont(fuente);
        g.drawString("ITZCOATL ORTEGA SANTIAGO", 280, 673);
        Font fuente2 = new Font("Arial", Font.BOLD, 10);
        g.setFont(fuente2);
        g.setColor(cg);
        g.drawString("CETI", 85, 675);

        g.setColor(c_claro);
        int[] ptsX = {140, 140, 160};
        int[] ptsY = {680, 660, 660};
        g.fillPolygon(ptsX, ptsY, 3);
        g.setColor(Color.BLACK);
        g.fillArc(646, 660, 40, 40, 90, -90);
        g.fillArc(39, 660, 40, 40, 90, 90);
        g.setColor(c_claro);
        g.fillArc(40, 660, 40, 40, 90, 90);
        g.fillArc(645, 660, 40, 40, 90, -90);

        g.setColor(Color.black);
        int[] pnsX = {140, 145, 165, 160};
        int[] pnsY = {680, 680, 660, 660};
        g.fillPolygon(pnsX, pnsY, 4);
        int[] pnnsX = {560, 565, 545, 540};
        int[] pnnsY = {680, 680, 660, 660};
        g.fillPolygon(pnnsX, pnnsY, 4);

        int[] pnssX = {565, 565, 545};
        int[] pnssY = {680, 660, 660};

        g.setColor(c_claro);
        g.fillPolygon(pnssX, pnssY, 3);

        g.setColor(c_dark);
        g.fillRect(565, 660, 100, 20);

        g.setColor(Color.black);
        g.fillRect(60, 660, 605, 1);
        g.fillRect(40, 680, 645, 1);
        g.setColor(cg);
        g.drawString("6P", 610, 675);

        int[] recttx = {200, 200, 240};
        int[] rectty = {565, 528, 565};
        int[] recttx1 = {469, 510, 510};
        int[] rectty1 = {565, 528, 565};
        g.setColor(Color.white);
        g.fillPolygon(recttx, rectty, 3);
        g.fillPolygon(recttx1, rectty1, 3);

        g.setColor(Color.black);
        g.drawPolygon(recttx, rectty, 3);
        g.drawPolygon(recttx1, rectty1, 3);

        Reloj(g);
    }

    public void Reloj(Graphics g) {
        Color c_dark = new Color(33, 2, 5);
        Color color4 = new Color(228, 193, 181);
        Color ama = new Color(243, 234, 119);
        Color c3 = new Color(242, 207, 196);
        Color c2 = new Color(185, 140, 101);
        
        //Circulos del reloj
        g.setColor(c2);
        g.fillOval(156, 200, 400, 400);

        g.setColor(ama);
        g.fillOval(165, 209, 382, 382);

        g.setColor(Color.gray);
        g.fillOval(167, 211, 378, 378);

        g.setColor(c3);
        g.fillOval(166, 210, 380, 380);

        g.setColor(color4);
        g.fillOval(226, 267, 266, 266);

        g.setColor(Color.black);
        g.fillRect(200, 565, 40, 73);
        g.fillRect(470, 565, 40, 73);

        g.setColor(c_dark);
        Font fo = new Font("Arial", Font.BOLD, 10);
        g.setFont(fo);
        g.drawString("12", 350, 245);
        g.drawString("1", 431, 262);
        g.drawString("2", 493, 327);
        g.drawString("3", 515, 404);
        g.drawString("4", 493, 485);
        g.drawString("5", 435, 544);
        g.drawString("6", 356, 565);
        g.drawString("7", 280, 544);
        g.drawString("8", 217, 485);
        g.drawString("9", 193, 405);
        g.drawString("10",214, 327);
        g.drawString("11",272, 265);

        //Marcas manecillas
        g.setColor(Color.black);
        for (int sec = 0; sec < 60; sec++) {
            int inicio;
            if (sec % 5 == 0) {
                inicio = 171;
                double seno = Math.sin(rad * sec);
                double coseno = Math.cos(rad * sec);
                int dxmin = (int) (inicio * seno);
                int dymin = (int) (inicio * coseno);

                int dxmax = (int) (188 * seno);
                int dymax = (int) (188 * coseno);
                //ancho de las manecillas de hora
                g.setColor(Color.BLACK);
                g.drawLine(358 + dxmin, 400 + dymin, 358 + dxmax, 400 + dymax);

            } else {
                g.setColor(Color.BLACK);
                inicio = 181;
                double seno = Math.sin(rad * sec);
                double coseno = Math.cos(rad * sec);
                int dxmin = (int) (inicio * seno);
                int dymin = (int) (inicio * coseno);

                int dxmax = (int) (190 * seno);
                int dymax = (int) (190 * coseno);

                g.drawLine(358 + dxmin, 400 + dymin, 358 + dxmax, 400 + dymax);

            }
        }

        update(g);

    }

    public static void main(String[] args) {
        new Proyecto_Reloj();
    }

    @Override
    public void run() {
        while (true) {
            try {
                repaint();
                hilo.sleep(1000);
            } catch (Exception e) {
            }
        }
    }

    private double getX(double ang, int rad) {
        double x = (double) rad * (double) (Math.sin(Math.toRadians(ang)));
        return x;
    }

    private double getY(double ang, int rad) {
        double y = (double) rad * (double) (Math.cos(Math.toRadians(ang)));
        return y;
    }

}
