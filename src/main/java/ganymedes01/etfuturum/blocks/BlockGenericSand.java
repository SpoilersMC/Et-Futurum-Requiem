package ganymedes01.etfuturum.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockGenericSand extends BlockFalling {
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons;
	protected final String[] types;
	protected boolean flipNames;

	protected int startMeta = 0;

	public boolean setFlippedNames(boolean flip) {
		return flip;
	}
	
	public boolean flippedNames() {
		return flipNames;
	}
	
	public BlockGenericSand(Material material, String... types) {
		super(material);
		this.types = types;
	}

	public String getNameFor(int meta) {
		return types[Math.max(Math.min(meta, types.length - 1), 0)];
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = startMeta; i < types.length; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icons[Math.max(Math.min(meta, types.length - 1), startMeta)];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		icons = new IIcon[types.length];
		for (int i = 0; i < types.length; i++)
			if ("".equals(types[i]))
				icons[i] = reg.registerIcon(getTextureName());
			else
				icons[i] = reg.registerIcon(getTextureName() + "_" + types[i]);
	}

}
