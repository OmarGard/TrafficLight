public class SemaforoBinario{

	boolean value;
	public CanvasExample canvas = null;
	SemaforoBinario( boolean initValue ){
		value = initValue;
	}
	
	public synchronized void P(int val){
	
		while( value == false ) {
			canvas.bufferLight(value);
			if(val == 1)
				canvas.producBlockProcess(true);
			if(val == 0)
				canvas.consumBlockProcess(true);
			Util.myWait(this); //en cola de procesos bloqueados
		}
		value = false;
		if(val == 1)
			canvas.producBlockProcess(false);
		if(val == 0)
			canvas.consumBlockProcess(false);
		canvas.bufferLight(value);
	
	}
	
	public synchronized void V(int val){
		value = true;
		canvas.bufferLight(value);
		notify();
	}
	
}
