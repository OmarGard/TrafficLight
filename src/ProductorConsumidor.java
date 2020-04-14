class ProductorConsumidor {
	
	public static void main(String args[]) {
		CanvasExample canvas = new CanvasExample();
		BufferLimitado buffer = new BufferLimitado(canvas);
		@SuppressWarnings("unused")
		Productor productor = new Productor( buffer ,canvas);
		@SuppressWarnings("unused")
		Consumidor consumidor = new Consumidor( buffer ,canvas);	
	}
	
}