package guiConfig;

public class TopBarProperties {
	 private int x;
	 private int y;
	 private int widith;
	 private int height;
	 
	 private static final int DEFAULTX = 0;
	 private static final int DEFAULTY = 0;
	 private static final int DEFAULT_WIDTH = 800;
	 private static final int DEFAULT_HEIGHT = 31;
	 

	 public TopBarProperties(int x,int y,int widith,int height) throws IllegalArgumentException
	 {
		 if(x<0||y<0||widith<=0||height<=0) throw new IllegalArgumentException("Dimensioni per il menu inferiori a 0.");
		 this.x=x;
		 this.height=height;
		 this.y=y;
		 this.widith=widith;
	 }

	 /**DEFAULT CONSTRUCTOR***/
	 public TopBarProperties(){
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
