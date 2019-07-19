package bloodandglory.common.tile;


import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IInteractionObject;

import static net.minecraft.tileentity.TileEntityFurnace.getItemBurnTime;

/**
 * 因为我不想重写熔炉界面了,我可以偷偷改一改原版Gui吗
 */
public class TileEntityBAGFurnace extends TileEntityBAGBasic implements ITickable, IInteractionObject {
    public int furnaceBurnTime;
    public int furnaceCookTime;
    public int currentItemBurnTime;
    public String customName;

    public TileEntityBAGFurnace() {
        super(4,"industrial_boiler");
    }

    @Override
    public String getName() {
        return this.hasCustomName() ? customName : "container.industrial_boiler";
    }

    @Override
    public boolean hasCustomName() {
        return this.customName != null && !this.customName.isEmpty();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        this.furnaceBurnTime = compound.getInteger("BurnTime");
        this.furnaceCookTime = compound.getInteger("CookTime");
        currentItemBurnTime = getItemBurnTime(inventory.get(2));
        if (compound.hasKey("CustomTime",8)){
            customName = compound.getString("CustomName");
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        compound.setInteger("BurnTime",furnaceBurnTime);
        compound.setInteger("CookTime",furnaceCookTime);
        if (hasCustomName()){
            compound.setString("CustomName",customName);
        }
        return compound;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isBurning(){
        return furnaceBurnTime > 0;
    }

    private boolean canSmelt(){
        if (inventory.get(0).isEmpty()){
            return false;
        }else {
            ItemStack itemStack = FurnaceRecipes.instance().getSmeltingResult(inventory.get(0));
            if (itemStack.isEmpty()) {
                return false;
            }
            if (inventory.get(2).isEmpty()) {
                return true;
            }
            if (!inventory.get(2).isItemEqual(itemStack)){
                return false;
            }
            int result = inventory.get(2).getCount() + itemStack.getCount();
            return result <= getInventoryStackLimit() && result <= inventory.get(2).getMaxStackSize();
        }
    }

    public void smeltItem(){

    }

    public int getCookTime(ItemStack stack){
        return 200;
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerFurnace(playerInventory,this);
    }

    @Override
    public String getGuiID() {
        return "minecraft:furnace";
    }

    @Override
    public void update()
    {
        //fixme:这是从原版直接抄来的因为开发时间不够，留到日后完善
        boolean flag = this.isBurning();
        boolean flag1 = false;

        if (this.isBurning())
        {
            --this.furnaceBurnTime;
        }

        if (!this.world.isRemote)
        {
            ItemStack itemstack = this.inventory.get(1);

            if (this.isBurning() || !itemstack.isEmpty() && !((ItemStack)this.inventory.get(0)).isEmpty())
            {
                if (!this.isBurning() && this.canSmelt())
                {
                    this.furnaceBurnTime = getItemBurnTime(itemstack);
                    this.currentItemBurnTime = this.furnaceBurnTime;

                    if (this.isBurning())
                    {
                        flag1 = true;

                        if (!itemstack.isEmpty())
                        {
                            Item item = itemstack.getItem();
                            itemstack.shrink(1);

                            if (itemstack.isEmpty())
                            {
                                ItemStack item1 = item.getContainerItem(itemstack);
                                this.inventory.set(1, item1);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt())
                {
                    ++this.furnaceCookTime;

                    if (this.furnaceCookTime == this.currentItemBurnTime)
                    {
                        this.furnaceCookTime = 0;
                        this.currentItemBurnTime = this.getCookTime(this.inventory.get(0));
                        this.smeltItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.furnaceCookTime = 0;
                }
            }
            else if (!this.isBurning() && this.furnaceCookTime > 0)
            {
                this.furnaceCookTime = MathHelper.clamp(this.furnaceCookTime - 2, 0, this.currentItemBurnTime);
            }

            if (flag != this.isBurning())
            {
                flag1 = true;
                BlockFurnace.setState(this.isBurning(), this.world, this.pos);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }
}
