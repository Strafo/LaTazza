package guiConfig;

public class MenuPaneProperties {
	
	 private int x;
	 private int y;
	 private int width;
	 private int height;
	 private int gradient;
	 
	 private static final int DEFAULTX = 0;
	 private static final int DEFAULTY = 0;
	 private static final int DEFAULT_WIDTH = 190;
	 private static final int DEFAULT_HEIGHT = 500;
	 private static final int DEFAULT_GRADIENT = 1000;
	 

	 public MenuPaneProperties(int x,int y,int width,int height,int gradient) throws IllegalArgumentException
	 {
		 if(x<0||y<0||width<=0||height<=0||gradient<=0) throw new IllegalArgumentException("Dimensioni per il menu inferiori a 0.");
		 this.x=x;
		 this.height=height;
		 this.y=y;
		 this.width=width;
		 this.gradient=gradient;
	 }

	 /**DEFAULT CONSTRUCTOR***/
	 public MenuPaneProperties(){
		 this(DEFAULTX,DEFAULTY,DEFAULT_WIDTH,DEFAULT_HEIGHT,DEFAULT_GRADIENT);
	 }

	 /**PROPERTIES GETTER**/
	 public int getX() {
		 return x;
	 }

	 public int getY() {
		 return y;
	 }

	 public int getWidth() {
		 return width;
	 }

	 public int getHeight() {
		 return height;
	 }
	 
	 public int getGradient() {
		 return gradient;
	 }
}
