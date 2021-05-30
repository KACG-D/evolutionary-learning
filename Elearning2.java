import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Elearning2 extends JPanel{
	int c=1;
	public static void main(String[] args) {

		JFrame frame = new JFrame();

	    Elearning2 app = new Elearning2();
	    frame.getContentPane().add(app);

	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setBounds(10, 10, 400, 400);
	    frame.setTitle("進化的学習2");
	    frame.setVisible(true);
	    while(app.notfinished){
			app.repaint();
		}
	}
	boolean notfinished =true;
	static final double EPS = 0.00001;
	double res[][][]=new double[301][301][2];
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.translate(0, 10);
		g.setColor(Color.WHITE);
		g.fillRect(0, -10, 400,410);
		for(int i=0;i<=300;i++){
			for(int j=0;j<=300;j++){
				if(res[i][j][0]!=0&&res[i][j][1]!=0){
					continue;
				}
				double x = j/300.0;
				double y = i/300.0;
				int count =0;
				for(int k=0;k<10000;k++){
					double x2 = nextX(x,y);
					double y2 = nextY(x,y);
					if(Math.abs(x-x2)+Math.abs(y-y2)<EPS){
						res[i][j][0]=x;
						res[i][j][1]=y;

						break;
					}
					x=x2;
					y=y2;
					count++;
				}
			}
		}
		for(int i=0;i<=300;i++){
			for(int j=0;j<=300;j++){
				float xc = (float)res[i][j][0];
				float yc = (float)res[i][j][1];

				g.setColor(new Color(xc,yc,1));

				int x = j;
				int y = 300-i;
				g.fillRect(x+50, y, 1, 1);

			}
		}

		g.setColor(Color.BLACK);
		for(int i=1;i<10;i++){
			for(int j=1;j<10;j++){
				double x = j/10.0;
				double y = i/10.0;
				int count =0;
				for(int k=0;k<10000;k++){
					double x2 = nextX(x,y);
					double y2 = nextY(x,y);
					g.drawLine((int)(x*300)+50, (int)((1-y)*300), (int)(x2*300)+50, (int)((1-y2)*300));
					if(Math.abs(x-x2)+Math.abs(y-y2)<EPS){
						break;
					}
					x=x2;
					y=y2;
					count++;

				}
			}
		}

		//g.drawRect(50, 0, 300, 300);
		for(int i=0;i<=10;i++){
			g.drawLine(48, i*30, 52, i*30);
			g.drawString(String.format("%.2f",(10-i)/10.0),10,i*30+5);
			g.drawLine(i*30+50, 298, i*30+50, 302);
			g.drawString(String.format("%.2f",i/10.0),i*30+50-5,320);
		}
		g.drawString(String.format("gen %d",c),10,340);
		c++;
		try {
            Thread.sleep(10);
        } catch(InterruptedException e){
            e.printStackTrace();
        }

	}

	double nextX(double x, double y){
		return x+0.1*x*(1-x)*(2-3*y);
	}
	double nextY(double x, double y){
		return y+0.1*y*(1-y)*(1-x);
	}
}
