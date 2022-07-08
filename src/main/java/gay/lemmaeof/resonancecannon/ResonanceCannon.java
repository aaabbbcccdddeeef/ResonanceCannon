package gay.lemmaeof.resonancecannon;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResonanceCannon implements ModInitializer {
	public static final String MODID = "resonancecannon";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	public static final Item RESONANCE_CANNON = Registry.register(
			Registry.ITEM,
			new ResourceLocation(MODID, "resonance_cannon"),
			new ResonanceCannonItem(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).stacksTo(1)));

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("BOOM!");
	}
}
