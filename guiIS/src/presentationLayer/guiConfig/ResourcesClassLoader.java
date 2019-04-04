package presentationLayer.guiConfig;

import java.util.Objects;
import javax.swing.ImageIcon;

//class to load imageIcon
public class ResourcesClassLoader{

    private static ClassLoader classLoader = ClassLoader.getSystemClassLoader();

    private static ImageIcon iconaTazza= retriveIcon("iconaTazza.png");
    private static ImageIcon iconaTazza2= retriveIcon("iconaTazza2.png");
    private static ImageIcon iconaTazzaWhite= retriveIcon("iconaTazzaW.png");
    private static ImageIcon iconaStatoB25= retriveIcon("statoB25.png");
    private static ImageIcon iconaStatoW25= retriveIcon("statoW25.png");
    private static ImageIcon iconaVenditaW25= retriveIcon("venditaW25.png");
    private static ImageIcon iconaVenditaB25= retriveIcon("venditaB25.png");
    private static ImageIcon iconaRifornimentoW25= retriveIcon("rifornimentoW25.png");
    private static ImageIcon iconaRifornimentoB25= retriveIcon("rifornimentoB25.png");
    private static ImageIcon iconaPagamentoW25= retriveIcon("pagamentoW25.png");
    private static ImageIcon iconaPagamentoB25= retriveIcon("pagamentoB25.png");
    private static ImageIcon iconaGestioneW25= retriveIcon("gestioneW25.png");
    private static ImageIcon iconaGestioneB25= retriveIcon("gestioneB25.png");
    private static ImageIcon iconaCloseW= retriveIcon("closeW.png");
    private static ImageIcon iconaCloseR= retriveIcon("closeRed.png");
    private static ImageIcon iconaMinW= retriveIcon("minimizeW.png");
    private static ImageIcon iconaVenditaB32= retriveIcon("venditaB32.png");
    private static ImageIcon iconaStatoB32= retriveIcon("statoB32.png");
    private static ImageIcon iconaRifornimentoB32= retriveIcon("rifornimentoB32.png");
    private static ImageIcon iconaPagamentoB32= retriveIcon("pagamentoB32.png");
    private static ImageIcon iconaGestioneB32= retriveIcon("gestioneB32.png");
    private static ImageIcon iconaCassa= retriveIcon("cassa.png");
    private static ImageIcon iconaMagazzino= retriveIcon("magazzino.png");
    private static ImageIcon iconaDebiti= retriveIcon("debiti.png");
    private static ImageIcon iconaAggPersonale= retriveIcon("iconAggiuntaUtente.png");
    private static ImageIcon iconaRimPersonale= retriveIcon("iconRimozioneUtente.png");

    public static ImageIcon retriveIcon(String iconAddress) {
        String path=System.getProperty("user.dir")+"/guiIS/src/iconeGui/"+iconAddress;
        return new ImageIcon(Objects.requireNonNull(path));
    }

    public static ImageIcon getIconW(String nomeIcona)
    {
        return new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/presentationLayer/iconeGui/"+nomeIcona+"W25.png");
    }

    public static ImageIcon getIconB(String nomeIcona)
    {
        return new ImageIcon(System.getProperty("user.dir")+"/guiIS/src/presentationLayer/iconeGui/"+nomeIcona+"B25.png");
    }

    public static ImageIcon getIconTazza() {
        return iconaTazza;
    }

    public static ImageIcon getIconTazza2() {
        return iconaTazza2;
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

    public static ImageIcon getIconCloseW() {
        return iconaCloseW;
    }

    public static ImageIcon getIconCloseR() {
        return iconaCloseR;
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

    public static ImageIcon getIconaAggPersonale() {
        return iconaAggPersonale;
    }

    public static ImageIcon getIconaRimPersonale() {
        return iconaRimPersonale;
    }

}
