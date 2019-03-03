package guiConfig;

import java.util.MissingResourceException;
import java.util.Objects;
import javax.swing.ImageIcon;

//class to load imageIcon
public class ResourcesClassLoader{

    private static ClassLoader classLoader = ClassLoader.getSystemClassLoader();






    private static ImageIcon iconaTazza=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/iconaTazza2.png");
    private static ImageIcon iconaStatoB25=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/statoB25.png");
    private static ImageIcon iconaStatoW25=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/statoW25.png");
    private static ImageIcon iconaVenditaW25=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/venditaW25.png");
    private static ImageIcon iconaVenditaB25=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/venditaB25.png");
    private static ImageIcon iconaRifornimentoW25=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/rifornimentoW25.png");
    private static ImageIcon iconaRifornimentoB25=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/rifornimentoB25.png");
    private static ImageIcon iconaPagamentoW25=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/pagamentoW25.png");
    private static ImageIcon iconaPagamentoB25=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/pagamentoB25.png");
    private static ImageIcon iconaGestioneW25=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/gestioneW25.png");
    private static ImageIcon iconaGestioneB25=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/gestioneB25.png");
    private static ImageIcon iconaCloseB=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/closeDark.png");
    private static ImageIcon iconaCloseR=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/closeRed.png");
    private static ImageIcon iconaMinB=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/minimizeD.png");
    private static ImageIcon iconaMinW=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/minimizeW.png");
    private static ImageIcon iconaVenditaB32=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/venditaB32.png");
    private static ImageIcon iconaStatoB32=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/statoB32.png");
    private static ImageIcon iconaRifornimentoB32=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/rifornimentoB32.png");
    private static ImageIcon iconaPagamentoB32=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/pagamentoB32.png");
    private static ImageIcon iconaGestioneB32=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/gestioneB32.png");
    private static ImageIcon iconaCassa=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/cassa.png");
    private static ImageIcon iconaMagazzino=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/magazzino.png");
    private static ImageIcon iconaDebiti=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/debiti.png");


    private static ImageIcon iconaVenditaB50 ;//=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/venditaB50.png");
    private static ImageIcon iconaStatoB50 ;//=new ImageIcon(System.getProperty("user.dir")+"/guiIs/src/iconeGui/statoB50.png");

    private static ImageIcon iconaGestioneB50;
    private static ImageIcon iconaRifornimentoB50;
    private static ImageIcon iconaPagamentoB50;


    //iconaStatoB50 = retriveIcon("statoB50.png");


    /*static{
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
*/
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
}
