package guiConfig;

import java.util.Objects;
import javax.swing.ImageIcon;

//class to load imageIcon
public class ResourcesClassLoader{

    private static ClassLoader classLoader = ClassLoader.getSystemClassLoader();


    private static ImageIcon iconaTazza=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/iconaTazza2.png");
    private static ImageIcon iconaTazzaBrown=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/iconaTazzaBrown.png");
    private static ImageIcon iconaTazzaWhite=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/iconaTazzaW.png");
    private static ImageIcon iconaTazza16=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/iconaTazza16.png");
    private static ImageIcon iconaTazza25=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/iconaTazza25.png");
    private static ImageIcon iconaTazza32=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/iconaTazza32.png");
    private static ImageIcon iconaTazza50=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/iconaTazza50.png");
    private static ImageIcon iconaTazza64=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/iconaTazza64.png");
    private static ImageIcon iconaStatoB25=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/statoB25.png");
    private static ImageIcon iconaStatoW25=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/statoW25.png");
    private static ImageIcon iconaVenditaW25=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/venditaW25.png");
    private static ImageIcon iconaVenditaB25=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/venditaB25.png");
    private static ImageIcon iconaRifornimentoW25=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/rifornimentoW25.png");
    private static ImageIcon iconaRifornimentoB25=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/rifornimentoB25.png");
    private static ImageIcon iconaPagamentoW25=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/pagamentoW25.png");
    private static ImageIcon iconaPagamentoB25=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/pagamentoB25.png");
    private static ImageIcon iconaGestioneW25=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/gestioneW25.png");
    private static ImageIcon iconaGestioneB25=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/gestioneB25.png");
    private static ImageIcon iconaCloseB=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/closeDark.png");
    private static ImageIcon iconaCloseR=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/closeRed.png");
    private static ImageIcon iconaMinB=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/minimizeD.png");
    private static ImageIcon iconaMinW=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/minimizeW.png");
    private static ImageIcon iconaVenditaB32=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/venditaB32.png");
    private static ImageIcon iconaStatoB32=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/statoB32.png");
    private static ImageIcon iconaRifornimentoB32=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/rifornimentoB32.png");
    private static ImageIcon iconaPagamentoB32=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/pagamentoB32.png");
    private static ImageIcon iconaGestioneB32=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/gestioneB32.png");
    private static ImageIcon iconaCassa=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/cassa.png");
    private static ImageIcon iconaMagazzino=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/magazzino.png");
    private static ImageIcon iconaDebiti=new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/debiti.png");


    public static ImageIcon retriveIcon(String iconAddress) {
        String path=System.getProperty("java.class.path")+"/guiIS/src/iconeGui/"+iconAddress;
        System.out.println("Path:"+path);
        return new ImageIcon(Objects.requireNonNull(classLoader.getResource(path)));
    }

    public static ImageIcon getIconW(String nomeIcona)
    {
        return new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/"+nomeIcona+"W25.png");
    }

    public static ImageIcon getIconB(String nomeIcona)
    {
        return new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/iconeGui/"+nomeIcona+"B25.png");
    }

    public static ImageIcon getIconTazza() {
        return iconaTazza;
    }

    public static ImageIcon getIconTazza16() {
        return iconaTazza16;
    }

    public static ImageIcon getIconTazza25() {
        return iconaTazza25;
    }

    public static ImageIcon getIconTazza32() {
        return iconaTazza32;
    }

    public static ImageIcon getIconTazza50() {
        return iconaTazza64;
    }

    public static ImageIcon getIconTazza64() {
        return iconaTazza64;
    }

    public static ImageIcon getIconTazzaBrown() {
        return iconaTazzaBrown;
    }

    public static ImageIcon getIconTazzaW() {
        return iconaTazzaWhite;
    }

    public static ImageIcon getIconStatoB25() {
        return iconaStatoB25;
    }

    public static ImageIcon getIconStatoW25() {
        return iconaStatoW25;
    }

    public static ImageIcon getIconVenditeB25() {
        return iconaVenditaB25;
    }

    public static ImageIcon getIconVenditeW25() {
        return iconaVenditaW25;
    }

    public static ImageIcon getIconRifornimentoB25() {
        return iconaRifornimentoB25;
    }

    public static ImageIcon getIconRifornimentoW25() {
        return iconaRifornimentoW25;
    }

    public static ImageIcon getIconGestionePB25() {
        return iconaGestioneB25;
    }

    public static ImageIcon getIconGestionePW25() {
        return iconaGestioneW25;
    }

    public static ImageIcon getIconPagamentoB25() {
        return iconaPagamentoB25;
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
