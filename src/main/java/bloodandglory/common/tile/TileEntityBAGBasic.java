package bloodandglory.common.tile;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nonnull;

public class TileEntityBAGBasic extends TileEntity implements ISidedInventory {
    private final String name;//保存TileEntity的名字
    protected NonNullList<ItemStack> inventory;
    protected final ItemStackHandler inventoryHandler;

    public TileEntityBAGBasic(int size,String name){
        this.inventoryHandler= new ItemStackHandler(this.inventory = NonNullList.withSize(size,ItemStack.EMPTY)){
            @Override
            public void setSize(int size1){
                this.stacks = TileEntityBAGBasic.this.inventory = NonNullList.withSize(size,ItemStack.EMPTY);
            }

            @Override
            protected void onContentsChanged(int slot){
                if (TileEntityBAGBasic.this.hasWorld()){
                    TileEntityBAGBasic.this.markDirty();
                }
            }
        };
        this.name = name;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        readInventoryNBT(compound);
    }

    protected void readInventoryNBT(NBTTagCompound nbt){
        this.clear();
        if (nbt.hasKey("Inventory", Constants.NBT.TAG_COMPOUND)){
            this.inventoryHandler.deserializeNBT(nbt.getCompoundTag("Inventory"));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        this.writeInvnetoryNBT(compound);
        return compound;
    }

    protected void writeInvnetoryNBT(NBTTagCompound nbt){
        NBTTagCompound inventoryNBT = this.inventoryHandler.serializeNBT();
        nbt.setTag("Inventory",inventoryNBT);
    }

    @Override
    public int getSizeInventory() {
        return this.inventoryHandler.getSlots();
    }

    @Override
    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    @Override
    @MethodsReturnNonnullByDefault
    public ItemStack getStackInSlot(int slot) {
        this.accessSlot(slot);
        return this.inventoryHandler.getStackInSlot(slot);
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        int[] slots = new int[getSizeInventory()];
        for (int i = 0; i < slots.length; i++) {
            slots[i] = i;
        }

        return slots;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, EnumFacing side) {
        this.accessSlot(slot);
        return isItemValidForSlot(slot, stack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, EnumFacing side) {
        this.accessSlot(slot);
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        this.accessSlot(index);
        return this.inventoryHandler.extractItem(index, count, false);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        this.accessSlot(index);
        return this.inventoryHandler.extractItem(index, !this.inventoryHandler.getStackInSlot(index).isEmpty() ? this.inventoryHandler.getStackInSlot(index).getCount() : 0, false);
    }

    @Override
    public void setInventorySlotContents(int index, @Nonnull ItemStack stack) {
        this.accessSlot(index);
        this.inventoryHandler.setStackInSlot(index, stack);
    }

    @Override
    public void clear() {
        for(int i = 0; i < this.inventoryHandler.getSlots(); i++) {
            this.inventoryHandler.setStackInSlot(i, ItemStack.EMPTY);
        }
    }

    /**
     * Called before a slot is accessed
     * @param slot
     */
    protected void accessSlot(int slot) {

    }

    private IItemHandler handlerUp = new SidedInvWrapper(this, EnumFacing.UP);
    private IItemHandler handlerDown = new SidedInvWrapper(this, EnumFacing.DOWN);
    private IItemHandler handlerNorth = new SidedInvWrapper(this, EnumFacing.NORTH);
    private IItemHandler handlerSouth = new SidedInvWrapper(this, EnumFacing.SOUTH);
    private IItemHandler handlerEast = new SidedInvWrapper(this, EnumFacing.EAST);
    private IItemHandler handlerWest = new SidedInvWrapper(this, EnumFacing.WEST);
    private IItemHandler handlerNull = new InvWrapper(this);

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == null) {
                return (T) handlerNull;
            }
            switch (facing) {
                case DOWN:
                    return (T) this.handlerDown;
                case UP:
                    return (T) this.handlerUp;
                case NORTH:
                    return (T) this.handlerNorth;
                case SOUTH:
                    return (T) this.handlerSouth;
                case WEST:
                    return (T) this.handlerWest;
                case EAST:
                    return (T) this.handlerEast;
            }
        }
        return super.getCapability(capability, facing);
    }
}
