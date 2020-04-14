public class SemaforoContador {

	int value;
	public CanvasExample canvas = null;
	public SemaforoContador(int initValue){
		value = initValue;
	}

	public synchronized void P(int val) {
		value--;
		if( value < 0 ) {
			Util.myWait( this );
		}
	}
	
	public synchronized void V(int val) {	
		value++;
		if( value <= 0 )
		notify();
	}
	
}