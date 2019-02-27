package guiConfig;

public class PanelsProperties {
	
	private int x;
	private int y;
	private int widith;
	private int height;
	 
	private static final int DEFAULTX = 190;
	private static final int DEFAULTY = 31;
	private static final int DEFAULT_WIDTH = 610;
	private static final int DEFAULT_HEIGHT = 500;
	 

	public PanelsProperties(int x,int y,int widith,int height) throws IllegalArgumentException
	{
		if(x<=0||y<=0||widith<=0||height<=0) throw new IllegalArgumentException("Dimensioni per il panello stato inferiori a 0.");
		this.x=x;
		this.height=height;
		this.y=y;
		this.widith=widith;
	}

	/**DEFAULT CONSTRUCTOR***/
	public PanelsProperties(){
		this(DEFAULTX,DEFAULTY,DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}

	/**PROPERTIES GETTER**/
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return widith;
	}

	public int getHeight() {
		return height;
	}
}
