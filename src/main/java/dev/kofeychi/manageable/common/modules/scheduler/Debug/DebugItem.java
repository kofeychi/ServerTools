package dev.kofeychi.manageable.common.modules.scheduler.Debug;

import dev.kofeychi.manageable.common.modules.scheduler.Scheduler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class DebugItem extends Item {
    public DebugItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(world.isClient) return TypedActionResult.pass(user.getStackInHand(hand));
        Scheduler.SERVER.add(
                new Scheduler.Schedule(
                        10,
                        ()->{user.sendMessage(Text.literal("zhopa"),false);}
                )
        );
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
