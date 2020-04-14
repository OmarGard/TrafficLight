import java.util.Random;

class Productor implements Runnable {

	BufferLimitado b = null;
	CanvasExample canvas;
	public Productor( BufferLimitado initb , CanvasExample canvas) {
		b = initb;
		new Thread( this ).start();
		this.canvas=canvas;
	}
	
	public void run() {
		double item;
		Random r = new Random();
		while( true ){
			item = r.nextDouble();
			System.out.println( "Artículo producido " + item );
			b.deposit( item );
			canvas.setT_Making(400);
			canvas.making++;
			Util.mySleep(400);
		}
	}
	
}