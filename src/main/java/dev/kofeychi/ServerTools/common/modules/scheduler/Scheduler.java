package dev.kofeychi.ServerTools.common.modules.scheduler;

import net.fabricmc.api.EnvType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.function.Supplier;

public class Scheduler {
    public static Scheduler SERVER = new Scheduler(EnvType.SERVER);
    public static Scheduler CLIENT = new Scheduler(EnvType.CLIENT);

    private ArrayList<Schedule> instances = new ArrayList<>();
    public EnvType env;

    public Scheduler(EnvType envType) {
        env = envType;
    }

    public void tick(){
        instances.forEach(Schedule::tick);
        instances.removeIf(i->i.markForRemoval);
    }
    public void assertThread(String action){
        if(env == EnvType.CLIENT&&Thread.currentThread().getName() != "Render thread"){
            throw new IllegalStateException("You can only "+action+" Client Schedule task on Client!");
        } else if(env == EnvType.SERVER&&Thread.currentThread().getName() != "Server thread"){
            throw new IllegalStateException("You can only "+action+" Server Schedule task on Server!");
        }
    }
    public boolean add(Schedule schedule){
        assertThread("create");
        return instances.add(schedule);
    }
    public boolean revoke(UUID id){
        assertThread("revoke");
        int i = 0;for (Iterator<Schedule> it = instances.iterator(); it.hasNext(); i++) {
            Schedule schedule = (Schedule) it.next();
            if(schedule.uuid.toString().equalsIgnoreCase(id.toString())){
                instances.remove(i);
                return true;
            }
        }
        return false;
    }
    public Schedule get(UUID id){
        assertThread("get");
        int i = 0;for (Iterator<Schedule> it = instances.iterator(); it.hasNext(); i++) {
            Schedule schedule = (Schedule) it.next();
            if(schedule.uuid.toString().equalsIgnoreCase(id.toString())){
                return schedule;
            }
        }
        return null;
    }

    public static class Schedule {
        public UUID uuid;
        public long expirationDate;
        public Supplier<Void> action;
        public boolean markForRemoval = false;

        public Schedule(long tickTime,Supplier<Void> operation){
            uuid = UUID.randomUUID();
            expirationDate = tickTime;
            action = operation;
        }
        public void tick(){
            if(!markForRemoval) {
                expirationDate--;
                if(expirationDate==0){
                    run();
                }
            }
        }
        public void run(){
            action.get();
            markForRemoval = true;
        }
    }
}
