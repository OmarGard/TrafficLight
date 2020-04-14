class Consumidor implements Runnable {

	BufferLimitado b = null;
	CanvasExample canvas = null;
	public Consumidor( BufferLimitado initb , CanvasExample canvas) {

		b = initb;
		new Thread( this ).start();
		this.canvas = canvas;
	}
	
	public void run() {
		double item;
		while( true ){
			item = b.fetch();
			System.out.println("Artículo extraído " + item );
			canvas.setT_Consuming(4000);
			canvas.consuming++;
			Util.mySleep(400);
		}
	}

}