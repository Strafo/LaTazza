/*package guiConfig;

import java.util.MissingResourceException;
import java.util.Objects;
import javax.swing.ImageIcon;

public class MyClassLoader{
	
		private static ClassLoader classLoader = ClassLoader.getSystemClassLoader();
	
		private static ImageIcon iconaTazza;
		private static ImageIcon iconaStatoB25;
		private static ImageIcon iconaStatoW25;
		private static ImageIcon iconaVenditaW25;
		private static ImageIcon iconaVenditaB25;
		private static ImageIcon iconaRifornimentoW25;
		private static ImageIcon iconaRifornimentoB25;
		private static ImageIcon iconaPagamentoW25;
		private static ImageIcon iconaPagamentoB25;
		private static ImageIcon iconaGestioneW25;
		private static ImageIcon iconaGestioneB25;
		private static ImageIcon iconaVenditaB50;
		private static ImageIcon iconaRifornimentoB50;
		private static ImageIcon iconaGestioneB50;
		private static ImageIcon iconaStatoB50;
		private static ImageIcon iconaPagamentoB50;
		private static ImageIcon iconaCloseB;
		private static ImageIcon iconaCloseR;
		private static ImageIcon iconaMinB;
		private static ImageIcon iconaMinW;
		private static ImageIcon iconaVenditaB32;
		private static ImageIcon iconaStatoB32;
		private static ImageIcon iconaRifornimentoB32;
		private static ImageIcon iconaPagamentoB32;
		private static ImageIcon iconaGestioneB32;
		private static ImageIcon iconaCassa;
		private static ImageIcon iconaMagazzino;
		private static ImageIcon iconaDebiti;
		
		static{
			try {
	            iconaTazza = retriveIcon("iconaTazza2.png");
	            iconaStatoB25 = retriveIcon("statoB25.png");
	            iconaStatoW25 = retriveIcon("statoW25.png");
	            iconaVenditaW25 = retriveIcon("venditaW25.png");
	            iconaVenditaB25 = retriveIcon("venditaB25.png");
	            iconaRifornimentoW25 = retriveIcon("rifornimentoW25.png");
	            iconaRifornimentoB25 = retriveIcon("rifornimentoB25.png");
	            iconaPagamentoW25 = retriveIcon("pagamentoW25.png");
	            iconaPagamentoB25 = retriveIcon("pagamentoB25.png");
	            iconaGestioneW25 = retriveIcon("gestioneW25.png");
	            iconaGestioneB25 = retriveIcon("gestioneB25.png");
	            iconaCloseB = retriveIcon("closeDark.png");
	            iconaCloseR = retriveIcon("closeRed.png");
	            iconaMinB = retriveIcon("minimizeD.png");
	            iconaMinW = retriveIcon("minimizeW.png");
	            //iconaStatoB50 = retriveIcon("statoB50.png");
	            //iconaVenditaB50 = retriveIcon("venditaB50.png");
	            iconaVenditaB32 = retriveIcon("venditaB32.png");
	            iconaStatoB32 = retriveIcon("statoB32.png");
	            iconaRifornimentoB32 = retriveIcon("rifornimentoB32.png");
	            iconaPagamentoB32 = retriveIcon("pagamentoB32.png");
	            iconaGestioneB32 = retriveIcon("gestioneB32.png");
	            iconaCassa = retriveIcon("cassa.png");
	            iconaMagazzino = retriveIcon("magazzino.png");
	            iconaDebiti = retriveIcon("debiti.png");
	            
	        } catch (NullPointerException e) {
	            throw new MissingResourceException("Impossible to load resource.", "ImageFile", "");
	        }
		}
		
		public static ImageIcon retriveIcon(String iconAddress) {
		    String path=System.getProperty("java.class.path")+"/guiIs/src/iconeGui/"+iconAddress;
		    System.out.println("Path:"+path);
			return new ImageIcon(Objects.requireNonNull(classLoader.getResource(path)));
		}
		
		//GETTER METHODS
		public static ImageIcon getIconTazza() {
			return iconaTazza;
		}
		
		public static ImageIcon getIconStatoB25() {
			return iconaStatoB25;
		}
		
		public static ImageIcon getIconStatoW25() {
			return iconaStatoW25;
		}
		public static ImageIcon getIconStatoB50() {
			return iconaStatoB50;
		}
		
		public static ImageIcon getIconVenditeB25() {
			return iconaVenditaB25;
		}
		
		public static ImageIcon getIconVenditeB50() {
			return iconaVenditaB50;
		}
		
		public static ImageIcon getIconVenditeW25() {
			return iconaVenditaW25;
		}

		public static ImageIcon getIconRifornimentoB25() {
			return iconaRifornimentoB25;
		}
		
		public static ImageIcon getIconRifornimentoB50() {
			return iconaRifornimentoB50;
		}
		
		public static ImageIcon getIconRifornimentoW25() {
			return iconaRifornimentoW25;
		}
		

		public static ImageIcon getIconGestionePB25() {
			return iconaGestioneB25;
		}
		
		public static ImageIcon getIconGestionePB50() {
			return iconaGestioneB50;
		}
		
		public static ImageIcon getIconGestionePW25() {
			return iconaGestioneW25;
		}

		public static ImageIcon getIconPagamentoB25() {
			return iconaPagamentoB25;
		}
		
		public static ImageIcon getIconPagamentoB50() {
			return iconaPagamentoB50;
		}
		
		public static ImageIcon getIconPagamentoW25() {
			return iconaPagamentoW25;
		}
		
		public static ImageIcon getIconCloseB() {
			return iconaCloseB;
		}
		
		public static ImageIcon getIconCloseR() {
			return iconaCloseR;
		}
		
		public static ImageIcon getIconMinB() {
			return iconaMinB;
		}
		
		public static ImageIcon getIconMinW() {
			return iconaMinW;
		}
		
		public static ImageIcon getIconPagamentoB32() {
			return iconaPagamentoB32;
		}
		
		public static ImageIcon getIconRifornimentoB32() {
			return iconaRifornimentoB32;
		}
		
		public static ImageIcon getIconVenditaB32() {
			return iconaVenditaB32;
		}
		
		public static ImageIcon getIconStatoB32() {
			return iconaStatoB32;
		}
		
		public static ImageIcon getIconGestioneB32() {
			return iconaGestioneB32;
		}
		
		public static ImageIcon getIconMagazzino() {
			return iconaMagazzino;
		}
		
		public static ImageIcon getIconCassa() {
			return iconaCassa;
		}
		
		public static ImageIcon getIconDebito() {
			return iconaDebiti;
		}		
}*/