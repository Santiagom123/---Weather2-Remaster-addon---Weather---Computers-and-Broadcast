package com.santiagom123.weather_broadcasting.TileEntitys;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.SimpleComponent;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.mrbt0907.weather2.api.WeatherAPI;
import net.mrbt0907.weather2.api.WindReader;
import net.mrbt0907.weather2.api.weather.WeatherEnum;
import net.mrbt0907.weather2.util.Maths;
import net.mrbt0907.weather2.util.WeatherUtil;
import net.mrbt0907.weather2.weather.storm.WeatherObject;

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

   public void update() {
      if (this.world != null && !this.world.isRemote) {
         Maths.Vec3 Vpos = new Maths.Vec3((double)this.pos.getX() + (double)0.0F, (double)(this.pos.getY() + 1), (double)this.pos.getZ() + (double)0.0F);
         this.Bpos = this.pos;
         this.closestStorm = WeatherAPI.getClosestWeather(this.world.provider.getDimension(), Vpos, (double)712.0F, 0, 10, new WeatherEnum.Type[0]);
         this.WindAngle = WindReader.getWindAngle(this.world, Vpos);
         this.WindSpeed = WindReader.getWindSpeed(this.world, Vpos);
         this.crudeTemp = WeatherUtil.getTemperature(this.world, this.Bpos);
         this.Humidity = WeatherUtil.getHumidity(this.world, this.Bpos);
         this.Pressure = WeatherUtil.getPressure(this.world, this.Bpos);
         this.CrudeDewPoint = WeatherUtil.getDewpoint(this.world, this.Bpos);
         this.CDewPoint = this.CrudeDewPoint;
         this.CTemperature = WeatherUtil.toCelsius(this.crudeTemp);
         this.FTemperature = WeatherUtil.toFahrenheit(this.crudeTemp);
         this.IsRaining = WeatherAPI.isPrecipitatingAt(this.world, Vpos.toBlockPos());
         if (this.closestStorm == null) {
            this.IsAStorm = false;
         } else {
            this.IsAStorm = this.closestStorm.getName() != null && !"Cloud".equals(this.closestStorm.getName());
         }

      }
   }

   public String getComponentName() {
      return "WindAdapter";
   }

   @Callback
   public Object[] At(Context context, Arguments args) {
      return new Object[]{"OK"};
   }

   @Callback
   public Object[] GetWindAngle(Context context, Arguments args) {
      return new Object[]{this.WindAngle};
   }

   @Callback
   public Object[] GetWindSpeed(Context context, Arguments args) {
      return new Object[]{this.WindSpeed};
   }

   @Callback
   public Object[] ClosestStorm(Context context, Arguments args) {
      return new Object[]{"type", this.closestStorm.getTypeName(), "dist", this.closestStorm.getPos().distance((double)this.pos.getX(), (double)this.pos.getY(), (double)this.pos.getZ()), "angle", this.closestStorm.getAngle(), "speed", this.closestStorm.getSpeed(), "name", this.closestStorm.getName(), "danger", this.closestStorm.isDangerous(), "isDying", this.closestStorm.isDying(), "stage", this.closestStorm.getStage(), "Wspeed", this.closestStorm.getWindSpeed(), "size", this.closestStorm.size};
   }

   @Callback
   public Object[] IsRaining(Context context, Arguments args) {
      return new Object[]{this.IsRaining};
   }

   @Callback
   public Object[] IsAStorm(Context context, Arguments args) {
      return new Object[]{this.IsAStorm};
   }

   @Callback
   public Object[] GetHumidity(Context context, Arguments args) {
      return new Object[]{this.Humidity};
   }

   @Callback
   public Object[] GetDewpoint(Context context, Arguments args) {
      return new Object[]{this.CDewPoint};
   }

   @Callback
   public Object[] GetPressure(Context context, Arguments args) {
      return new Object[]{this.Pressure};
   }

   @Callback
   public Object[] GetFarenheitTemperature(Context context, Arguments args) {
      return new Object[]{this.FTemperature};
   }

   @Callback
   public Object[] GetCelsiusTemperature(Context context, Arguments args) {
      return new Object[]{this.CTemperature};
   }
}
