class BufferLimitado{

	final int size = 10;
	double buffer[] = new double[size];
	int inBuf = 0, outBuf = 0;
	CanvasExample canvas;
	SemaforoBinario mutex = new SemaforoBinario(true);
	SemaforoContador isEmpty = new SemaforoContador(0);
	SemaforoContador isFull = new SemaforoContador( size );
	BufferLimitado(CanvasExample canvas){
		this.canvas = canvas;
		mutex.canvas = canvas;
		isEmpty.canvas = canvas;
		isFull.canvas = canvas;
	}
	public void deposit( double value ){
		isFull.P(1); // espera si el buffer está lleno
			canvas.producLight(true);
			Util.mySleep(1000);
			mutex.P(1); // asegura la exclusión mutua
				canvas.consumLight(false);
				canvas.consuming=0;
				buffer[inBuf] = value;
				canvas.fill(inBuf);
				inBuf = (inBuf + 1) % size;
				Util.mySleep(1000);
			mutex.V(1);
			canvas.consumLight(true);
			Util.mySleep(1000);
		isEmpty.V(1); // notifica a algún consumidor en espera
		canvas.producLight(false);
	}
	
	public double fetch(){	
		double value;
		
		isEmpty.P(0); // esperar si el buffer está vacío
			canvas.consumLight(true);
			Util.mySleep(1000);
			mutex.P(0);// // asegura la exclusión mutua
				canvas.producLight(false);
				canvas.making=0;
				value = buffer[outBuf]; // lee desde el buffer
				canvas.drop(outBuf);
				outBuf = (outBuf+1) % size;
				Util.mySleep(1000);
			mutex.V(0); 
			canvas.producLight(true);
			Util.mySleep(1000);
		isFull.V(0); // notifica a cualquier productor en espera
		canvas.consumLight(false);
		return value;
	}
}