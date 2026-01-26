package com.santiagom123.weather_broadcasting.TileEntitys;

import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.network.SimpleComponent;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;


import net.minecraft.util.math.BlockPos;
import net.mrbt0907.weather2.api.WindReader;
import net.mrbt0907.weather2.api.WeatherAPI;

import net.mrbt0907.weather2.util.WeatherUtil;

import li.cil.oc.api.machine.Context;
import li.cil.oc.api.machine.Arguments;
import net.mrbt0907.weather2.util.Maths;
import net.mrbt0907.weather2.weather.storm.WeatherObject;

import java.util.Objects;


public class TileDigitalRadar extends TileEntity implements SimpleComponent, ITickable {

    float WindAngle;
    float WindSpeed;

    float crudeTemp;
    float Humidity;
    float Pressure;
    float CrudeDewPoint;

    float CDewPoint;
    float CTemperature;
    float FTemperature;

    WeatherObject closestStorm;
    boolean IsRaining;
    boolean IsAStorm;
    BlockPos Bpos;


    @Override
    public void update() {
        if (world == null || world.isRemote) return;

        Maths.Vec3 Vpos = new Maths.Vec3(
                pos.getX() + 0.0,
                pos.getY() + 1,
                pos.getZ() + 0.0
        );


        Bpos = pos;

        closestStorm = WeatherAPI.getClosestWeather(
                world.provider.getDimension(),
                Vpos,
                712,
                0,
                10
        );

        WindAngle = WindReader.getWindAngle(world, Vpos);
        WindSpeed = WindReader.getWindSpeed(world, Vpos);

        crudeTemp = WeatherUtil.getTemperature(world, Bpos);
        Humidity = WeatherUtil.getHumidity(world, Bpos);
        Pressure = WeatherUtil.getPressure(world, Bpos);
        CrudeDewPoint = WeatherUtil.getDewpoint(world, Bpos);

        CDewPoint = CrudeDewPoint;
        CTemperature = WeatherUtil.toCelsius(crudeTemp);
        FTemperature = WeatherUtil.toFahrenheit(crudeTemp);

        IsRaining = WeatherAPI.isPrecipitatingAt(world, Vpos.toBlockPos());

        if (closestStorm == null) {
            IsAStorm = false;
        } else IsAStorm = closestStorm.getName() != null && !"Cloud".equals(closestStorm.getName());



    }

    @Override
    public String getComponentName() {
        return "WindAdapter";
    }

    @Callback
    public Object[] At(Context context, Arguments args) {
        return new Object[]{"OK"};
    }

    @Callback
    public Object[] GetWindAngle(Context context, Arguments args) {
        return new Object[]{WindAngle};
    }

    @Callback
    public Object[] GetWindSpeed(Context context, Arguments args) {
        return new Object[]{WindSpeed};
    }

    @Callback
    public Object[] ClosestStorm(Context context, Arguments args) {
        return new Object[]{
                "type" , closestStorm.getTypeName(),
                "dist", closestStorm.getPos().distance(pos.getX(), pos.getY(), pos.getZ()),
                "angle" , closestStorm.getAngle(),
                "speed" , closestStorm.getSpeed(),
                "name" , closestStorm.getName(),
                "danger" , closestStorm.isDangerous(),
                "isDying" , closestStorm.isDying(),
                "stage" , closestStorm.getStage(),
                "Wspeed" , closestStorm.getWindSpeed(),
                "size" , closestStorm.size



        };
    }

    @Callback
    public Object[] IsRainig(Context context, Arguments args) {
        return new Object[]{IsRaining};
    }

    @Callback
    public Object[] IsAStorm(Context context, Arguments args) {
        return new Object[]{IsAStorm};
    }

    @Callback
    public Object[] GetHumidity(Context context, Arguments args) {
        return new Object[]{Humidity};
    }

    @Callback
    public Object[] GetDewpoint(Context context, Arguments args) {
        return new Object[]{CDewPoint};
    }

    @Callback
    public Object[] GetPressure(Context context, Arguments args) {
        return new Object[]{Pressure};
    }

    @Callback
    public Object[] GetFarenheitTemperature(Context context, Arguments args) {
        return new Object[]{FTemperature};
    }

    @Callback
    public Object[] GetCelsiusTemperature(Context context, Arguments args) {
        return new Object[]{CTemperature};
    }
}
