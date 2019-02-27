package guiConfig;

public class ContentPaneProperties {
	private int x;
	 private int y;
	 private int width;
	 private int height;
	 private int border;
	 
	 private static final int DEFAULTX = 100;
	 private static final int DEFAULTY = 100;
	 private static final int DEFAULT_WIDTH = 1000;
	 private static final int DEFAULT_HEIGHT = 500;
	 
	 private static final int DEFAULT_BORDER = 0;

	 public ContentPaneProperties(int x,int y,int width,int height,int border ) throws IllegalArgumentException
	 {
		 if(x<=0||y<=0||width<=0||height<=0||border<0) throw new IllegalArgumentException("Dimensioni per il contentPane inferiori a 0.");
		 this.x=x;
		 this.height=height;
		 this.y=y;
		 this.width=width;
		 this.border=border;
	 }

	 /**DEFAULT CONSTRUCTOR***/
	 public ContentPaneProperties(){
		 this(DEFAULTX,DEFAULTY,DEFAULT_WIDTH,DEFAULT_HEIGHT,DEFAULT_BORDER);
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
	 
	 public int getBorder() {
		 return border;
	 }
}
