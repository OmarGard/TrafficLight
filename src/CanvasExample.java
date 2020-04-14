import java.awt.*;

import javax.swing.JFrame;
public class CanvasExample extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static int size = 10;
	public static boolean buffer[] = new boolean[size];
	public static boolean buffer_Light = true;
	public static boolean consum_Light = false;
	public static boolean produc_Light = false;
	public static boolean consumBlock_Process = false;
	public static boolean producBlock_Process = false;
	public static int making = 0;
	public static int consuming = 0;
	public static int t_making = 0;
	public static int t_consuming = 0;
	public static MyCanvas canvas = null;
	public CanvasExample() {
		JFrame f = new JFrame("Canvas example");
		canvas = new MyCanvas(this);
		f.add(canvas);
		f.setLayout(null);
		f.setSize(400,400);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String args[]) {
		new CanvasExample();
		for(int i=0;i<size;i++)
			buffer[i]=false;
	}
	public void fill(int pos) {
		buffer[pos]=true;
		canvas.repaint();
	}
	public void drop(int pos) {
		buffer[pos]=false;
		canvas.repaint();
	}
	public void bufferLight(boolean value) {
		buffer_Light=value;
		canvas.repaint();
	}
	public void consumLight(boolean value) {
		consum_Light=value;
		canvas.repaint();
	}
	public void producLight(boolean value) {
		produc_Light=value;
		canvas.repaint();
	}
	public void consumBlockProcess(boolean value) {
		consumBlock_Process = value;
		canvas.repaint();
	}
	public void producBlockProcess(boolean value) {
		producBlock_Process = value;
		canvas.repaint();
	}
	public void setT_Making(int value) {
		t_making = value;
		canvas.repaint();
	}
	public void setT_Consuming(int value) {
		t_consuming = value;
		canvas.repaint();
	}

}
class MyCanvas extends Canvas{
	public static final Color Very_light_yellow = new Color(222,238,164);
	public static final Color Dark_green = new Color(91,91,0);
	public static final Color Purple = new Color(127,127,255);
	public static final Color Dark_gray = new Color(64,64,64);
	public static final Color Magenta = new Color(255,0,255);
	public int band = 0;
	public static CanvasExample father = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MyCanvas(CanvasExample father) {
		setBackground(Color.gray);
		setSize(400,400);
		band = 0;
		this.father = father;
	}
	public void drawScenario(Graphics g) {
		int fontSize = 14;
		//Painting outer black rectangle
		g.setColor(Color.black);
		g.drawRect(10, 10, 360, 340);
		
		//Painting fonts
		g.setFont(new Font("Arial", Font.PLAIN, fontSize));
		g.drawString("Proceso Consumidor", 30, 30);
		g.drawString("Proceso Productor", 230, 30);
		g.setFont(new Font("Arial", Font.PLAIN, 11));
		g.drawString("Articulos consumiendo:"+father.consuming, 45, 150);
		g.drawString("Tiempo de consumo:"+father.t_consuming+"ms", 45, 170);
		g.drawString("Articulos produciendo:"+father.making, 200, 150);
		g.drawString("Tiempo de produccion:"+father.t_making+"ms", 200, 170);
		
		//Painting consumer and productor boxes
		g.setColor(Very_light_yellow);
		g.fillRect(45, 45, 80, 80);
		g.setColor(Purple);
		g.fillRect(250, 45, 80, 80);
		
		//Painting Traffic lights
		g.setColor(Dark_green);
		//Traffic Light boxes
		g.fillRect(130, 50, 30, 50);
		g.fillRect(215, 50, 30, 50);
		g.fillRect(180, 275, 30, 50);
		//Traffic Light Bulbs
		g.setColor(Dark_gray);
		g.fillOval(130, 50, 30, 25);
		g.fillOval(130, 75, 30, 25);
		g.fillOval(215, 50, 30, 25);
		g.fillOval(215, 75, 30, 25);
		g.fillOval(180, 275, 30, 25);
		g.fillOval(180, 300, 30, 25);
		if(father.consum_Light) {
			g.setColor(Color.green);
			g.fillOval(130, 75, 30, 25);
		}
		else
		{
			g.setColor(Color.red);
			g.fillOval(130, 50, 30, 25);
		}
		if(father.produc_Light) {
			g.setColor(Color.green);
			g.fillOval(215, 75, 30, 25);
		}
		else
		{
			g.setColor(Color.red);
			g.fillOval(215, 50, 30, 25);
		}
		if(father.buffer_Light) {
			g.setColor(Color.green);
			g.fillOval(180, 300, 30, 25);
		}
		else
		{
			g.setColor(Color.red);
			g.fillOval(180, 275, 30, 25);
		}
		
		//painting buffer
		g.setColor(Color.yellow);
		g.fillRect(40, 230, 280, 30);
		//painting drawers
		g.setColor(Color.black);
		for(int i=40;i<320;i+=28) {
			int drawer = (i - 40)/28;
			if(father.buffer[drawer])
			{
				g.setColor(Magenta);
				g.fillRect(i, 230, 28, 30);
			}
			g.setColor(Color.black);
			g.drawRect(i, 230, 28, 30);
		}
		
		//Draw Cables
		if(father.consumBlock_Process)
			g.setColor(Color.RED);
		else
			g.setColor(Color.black);
		//Consumer side
		g.drawLine(45, 85, 30, 85);
		g.drawLine(30, 85, 30, 300);
		g.drawLine(30, 300, 180, 300);
		//Producer side
		if(father.producBlock_Process)
			g.setColor(Color.RED);
		else
			g.setColor(Color.black);
		g.drawLine(210, 300, 350, 300);
		g.drawLine(350, 300, 350, 85);
		g.drawLine(350, 85, 335, 85);
	}
	public void drawInit(Graphics g) {
		int fontSize = 14;
		//Painting outer black rectangle
		g.setColor(Color.black);
		g.drawRect(10, 10, 360, 340);
		
		//Painting fonts
		g.setFont(new Font("Arial", Font.PLAIN, fontSize));
		g.drawString("Proceso Consumidor", 30, 30);
		g.drawString("Proceso Productor", 230, 30);
		g.setFont(new Font("Arial", Font.PLAIN, 11));
		g.drawString("Articulos consumiendo:"+father.consuming, 45, 150);
		g.drawString("Tiempo de consumo:"+father.t_consuming+"ms", 45, 170);
		g.drawString("Articulos produciendo:"+father.making, 200, 150);
		g.drawString("Tiempo de produccion:"+father.t_making+"ms", 200, 170);
		//Painting consumer and productor boxes
		g.setColor(Very_light_yellow);
		g.fillRect(45, 45, 80, 80);
		g.setColor(Purple);
		g.fillRect(250, 45, 80, 80);
		
		//Painting Traffic lights
		g.setColor(Dark_green);
		//Traffic Light boxes
		g.fillRect(130, 50, 30, 50);
		g.fillRect(215, 50, 30, 50);
		g.fillRect(180, 275, 30, 50);
		//Traffic Light Bulbs
		g.setColor(Dark_gray);
		g.fillOval(130, 50, 30, 25);
		g.fillOval(130, 75, 30, 25);
		g.fillOval(215, 50, 30, 25);
		g.fillOval(215, 75, 30, 25);
		g.fillOval(180, 275, 30, 25);
		g.fillOval(180, 300, 30, 25);
		
		//painting buffer
		g.setColor(Color.yellow);
		g.fillRect(40, 230, 280, 30);
		//painting drawers
		g.setColor(Color.black);
		for(int i=40;i<320;i+=28)
			g.drawRect(i, 230, 28, 30);
		//Draw Cables
		g.setColor(Color.black);
		g.drawLine(45, 85, 30, 85);
		g.drawLine(30, 85, 30, 300);
		g.drawLine(30, 300, 180, 300);
		g.drawLine(210, 300, 350, 300);
		g.drawLine(350, 300, 350, 85);
		g.drawLine(350, 85, 335, 85);
	}	
	public void paint(Graphics g) {
		super.paint(g);
		
		if(band == 0) {
			band = 1;
			drawInit(g);
		}
		else
		{
			drawScenario(g);
		}
		
	}
	
}
