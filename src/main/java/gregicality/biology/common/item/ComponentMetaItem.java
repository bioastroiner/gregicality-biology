package gregicality.biology.common.item;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.StandardMetaItem;

public class ComponentMetaItem extends StandardMetaItem {
    public static MetaItem<?>.MetaValueItem lab_slide,lab_slip,lab_slide_with_slip;

    @Override
    public void registerSubItems() {
        super.registerSubItems();
        lab_slide=addItem(1,"component.lab.slide");
        lab_slip=addItem(2,"component.lab.slip");
        lab_slide_with_slip=addItem(3,"component.lab.slide_with_slip");
    }
}
