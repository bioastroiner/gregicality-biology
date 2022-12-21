package gregicality.biology.client;

import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;

public class GCYBTextures {
    public static SimpleOverlayRenderer BIO1;
    public static SimpleOverlayRenderer BIO2;
    public static SimpleOverlayRenderer BIO3;

    public static void preInit(){
        BIO1=new SimpleOverlayRenderer("casings/bio/1");
        BIO2=new SimpleOverlayRenderer("casings/bio/2");
        BIO3=new SimpleOverlayRenderer("casings/bio/3");
    }
}
