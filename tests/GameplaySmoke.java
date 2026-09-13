package org.portmaster.spacegrunts;
import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
public class GameplaySmoke extends Main {
 int frame,play,previous=-1; long started=System.nanoTime();
 public static void main(String[] args) throws Exception { new Lwjgl3Application(new GameplaySmoke(),configuration()); }
 void key(int code,boolean down) {if(down)Gdx.input.getInputProcessor().keyDown(code);else Gdx.input.getInputProcessor().keyUp(code);}
 Object read(String name)throws Exception {java.lang.reflect.Field f=com.orangepixel.spacegrunts.myCanvas.class.getDeclaredField(name);f.setAccessible(true);return f.get(null);}
 @Override public void render() {
  frame++;
  if(frame>240){
   if(frame%90==0){key(Input.Keys.X,true);key(Input.Keys.ENTER,true);}
   if(frame%90==3){key(Input.Keys.X,false);key(Input.Keys.ENTER,false);}
  }
  if(GameState==43){
   play++;
   if(play%60==1)key(Input.Keys.RIGHT,true);
   if(play%60==5)key(Input.Keys.RIGHT,false);
  }
  super.render();
  java.nio.IntBuffer v=com.badlogic.gdx.utils.BufferUtils.newIntBuffer(4);Gdx.gl20.glGetIntegerv(com.badlogic.gdx.graphics.GL20.GL_VIEWPORT,v);
  if(v.get(0)!=layout.x || v.get(1)!=layout.y || v.get(2)!=layout.width || v.get(3)!=layout.height)throw new IllegalStateException("Viewport lost");
  if(physicalGraphics.getWidth()!=Integer.getInteger("spacegrunts.width",640) || physicalGraphics.getHeight()!=Integer.getInteger("spacegrunts.height",480))throw new IllegalStateException("Physical size changed");
  if(GameState!=previous){System.out.println("STATE "+frame+" "+GameState);previous=GameState;}
  if(frame%600==0)capture(System.getProperty("spacegrunts.output")+"/frame"+frame+".png");
  if(play>=360 || frame>=5400){
   capture(System.getProperty("spacegrunts.output")+"/final.png");
   if(play<360)throw new IllegalStateException("Gameplay not reached long enough: "+play);
   double seconds=(System.nanoTime()-started)/1e9;if(seconds<(frame-2)/60.0)throw new IllegalStateException("Frame cap exceeded");
   if(Boolean.getBoolean("spacegrunts.testSave")){try {Object profile=read("activePlayer"), player=read("myPlayer"), world=read("myWorld");
    profile.getClass().getMethod("saveGame",String.class,player.getClass(),world.getClass()).invoke(profile,PROFILEID,player,world);
    if(!Gdx.app.getPreferences(PROFILEID+"savedgame").contains("level")) throw new IllegalStateException("Save readback failed"); System.out.println("SAVE_READBACK_OK");}catch(Exception e){throw new IllegalStateException(e);}}
   System.out.println("GAMEPLAY_OK frames="+frame+" play="+play+" seconds="+seconds);Gdx.app.exit();
  }
 }
}
