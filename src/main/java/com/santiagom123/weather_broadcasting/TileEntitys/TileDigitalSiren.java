package com.santiagom123.weather_broadcasting.TileEntitys;

import com.santiagom123.weather_broadcasting.Utils;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.SimpleComponent;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;

public class TileDigitalSiren extends TileEntity implements SimpleComponent, ITickable {

    boolean isPlaying = false;
    int count = 0;
    float pitch;
    int time = 0;
    int delay;
    int delayStatic = 1;


    @Override
    public void update() {
        if(this.world != null && !this.world.isRemote) {

            if(this.isPlaying && !(count <= 0)) {

                count--;

                if (delay <= 0) {

                    world.playSound(null, pos, SoundEvents.BLOCK_NOTE_PLING, SoundCategory.BLOCKS, 1.0F, pitch);
                    delay = delayStatic;
                } else {
                    delay--;
                }
            }
            else if (isPlaying) {
                time = 0;
                isPlaying = false;
            }
        }
    }

    @Override
    public String getComponentName() {
        return "DigitalSiren";
    }

    @Callback(doc = "function(frecuency:int, duration:int, interval:int):boolean")
    public Object[] PlaySound(Context context, Arguments args) {
        float frequency = (float) args.checkDouble(0);
        this.time = (int) args.checkDouble(1);
        this.delayStatic = (int) args.checkDouble(2);
        this.count = time * 20;

        if (frequency < 20F || frequency > 2000F) {
            return new Object[]{null, "frecuency out of range (20-2000"};
        }
        if (this.time <= 0 || this.time > 600) {
            return new Object[]{null, "time out of range (1-600)"};
        }
        if (this.delayStatic <= 0 || this.delayStatic > 60) {
            return new Object[]{null, "interval out of range (1-60)"};
        }

        this.pitch = Utils.map(frequency, 20F, 2000F, 0.5F, 2.0F);

        this.delay = 0;
        this.isPlaying = true;

        return new Object[]{true};
    }

}
