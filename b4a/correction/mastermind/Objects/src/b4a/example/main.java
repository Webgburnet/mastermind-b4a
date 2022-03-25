package b4a.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
		BA.handler.postDelayed(new WaitForLayout(), 5);

	}
	private static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEvent(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}



public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}
public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}

public static void killProgram() {
     {
            Activity __a = null;
            if (main.previousOne != null) {
				__a = main.previousOne.get();
			}
            else {
                BA ba = main.mostCurrent.processBA.sharedProcessBA.activityBA.get();
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

BA.applicationContext.stopService(new android.content.Intent(BA.applicationContext, starter.class));
}
public anywheresoftware.b4a.keywords.Common __c = null;
public static int _aleatoire1 = 0;
public static int _aleatoire2 = 0;
public static int _aleatoire3 = 0;
public static int _aleatoire4 = 0;
public static int _compte_couleur1 = 0;
public static int _compte_couleur2 = 0;
public static int _compte_couleur3 = 0;
public static int _compte_couleur4 = 0;
public static int _envoie_couleur1 = 0;
public static int _envoie_couleur2 = 0;
public static int _envoie_couleur3 = 0;
public static int _envoie_couleur4 = 0;
public static int _bonne_place1 = 0;
public static int _bonne_place2 = 0;
public static int _bonne_place3 = 0;
public static int _bonne_place4 = 0;
public anywheresoftware.b4a.objects.LabelWrapper _couleur_aleatoire1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _couleur_aleatoire2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _couleur_aleatoire3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _couleur_aleatoire4 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _choix_couleur1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _choix_couleur2 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _choix_couleur3 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _choix_couleur4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _couleur1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _couleur2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _couleur3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _couleur4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _afficher_couleur1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _afficher_couleur2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _afficher_couleur3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _afficher_couleur4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _fond = null;
public anywheresoftware.b4a.objects.LabelWrapper _notice_pion = null;
public anywheresoftware.b4a.objects.LabelWrapper _pion1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _pion2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _pion3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _pion4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _resulat = null;
public anywheresoftware.b4a.objects.ButtonWrapper _debug = null;
public b4a.example.starter _starter = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=131072;
 //BA.debugLineNum = 131072;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=131075;
 //BA.debugLineNum = 131075;BA.debugLine="Activity.LoadLayout(\"Commencer\")";
mostCurrent._activity.LoadLayout("Commencer",mostCurrent.activityBA);
RDebugUtils.currentLine=131076;
 //BA.debugLineNum = 131076;BA.debugLine="compte_couleur1=0";
_compte_couleur1 = (int) (0);
RDebugUtils.currentLine=131077;
 //BA.debugLineNum = 131077;BA.debugLine="compte_couleur2=0";
_compte_couleur2 = (int) (0);
RDebugUtils.currentLine=131078;
 //BA.debugLineNum = 131078;BA.debugLine="compte_couleur3=0";
_compte_couleur3 = (int) (0);
RDebugUtils.currentLine=131079;
 //BA.debugLineNum = 131079;BA.debugLine="compte_couleur4=0";
_compte_couleur4 = (int) (0);
RDebugUtils.currentLine=131080;
 //BA.debugLineNum = 131080;BA.debugLine="envoie_couleur1=0";
_envoie_couleur1 = (int) (0);
RDebugUtils.currentLine=131081;
 //BA.debugLineNum = 131081;BA.debugLine="envoie_couleur2=0";
_envoie_couleur2 = (int) (0);
RDebugUtils.currentLine=131082;
 //BA.debugLineNum = 131082;BA.debugLine="envoie_couleur3=0";
_envoie_couleur3 = (int) (0);
RDebugUtils.currentLine=131083;
 //BA.debugLineNum = 131083;BA.debugLine="envoie_couleur4=0";
_envoie_couleur4 = (int) (0);
RDebugUtils.currentLine=131084;
 //BA.debugLineNum = 131084;BA.debugLine="Fond.Color=Colors.White";
mostCurrent._fond.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=131085;
 //BA.debugLineNum = 131085;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=262144;
 //BA.debugLineNum = 262144;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=262146;
 //BA.debugLineNum = 262146;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=196608;
 //BA.debugLineNum = 196608;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=196610;
 //BA.debugLineNum = 196610;BA.debugLine="End Sub";
return "";
}
public static String  _choix_couleur1_click() throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=589824;
 //BA.debugLineNum = 589824;BA.debugLine="Sub choix_couleur1_Click";
RDebugUtils.currentLine=589825;
 //BA.debugLineNum = 589825;BA.debugLine="compte_couleur1=compte_couleur1+1";
_compte_couleur1 = (int) (_compte_couleur1+1);
RDebugUtils.currentLine=589826;
 //BA.debugLineNum = 589826;BA.debugLine="If compte_couleur1=1 Then";
if (_compte_couleur1==1) { 
RDebugUtils.currentLine=589827;
 //BA.debugLineNum = 589827;BA.debugLine="couleur1.Color=Colors.Red";
mostCurrent._couleur1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
RDebugUtils.currentLine=589828;
 //BA.debugLineNum = 589828;BA.debugLine="envoie_couleur1=1";
_envoie_couleur1 = (int) (1);
 };
RDebugUtils.currentLine=589830;
 //BA.debugLineNum = 589830;BA.debugLine="If compte_couleur1=2 Then";
if (_compte_couleur1==2) { 
RDebugUtils.currentLine=589831;
 //BA.debugLineNum = 589831;BA.debugLine="couleur1.Color=Colors.Blue";
mostCurrent._couleur1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
RDebugUtils.currentLine=589832;
 //BA.debugLineNum = 589832;BA.debugLine="envoie_couleur1=2";
_envoie_couleur1 = (int) (2);
 };
RDebugUtils.currentLine=589834;
 //BA.debugLineNum = 589834;BA.debugLine="If compte_couleur1=3 Then";
if (_compte_couleur1==3) { 
RDebugUtils.currentLine=589835;
 //BA.debugLineNum = 589835;BA.debugLine="couleur1.Color=Colors.Yellow";
mostCurrent._couleur1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
RDebugUtils.currentLine=589836;
 //BA.debugLineNum = 589836;BA.debugLine="envoie_couleur1=3";
_envoie_couleur1 = (int) (3);
 };
RDebugUtils.currentLine=589838;
 //BA.debugLineNum = 589838;BA.debugLine="If compte_couleur1=4 Then";
if (_compte_couleur1==4) { 
RDebugUtils.currentLine=589839;
 //BA.debugLineNum = 589839;BA.debugLine="couleur1.Color=Colors.Green";
mostCurrent._couleur1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
RDebugUtils.currentLine=589840;
 //BA.debugLineNum = 589840;BA.debugLine="compte_couleur1=0";
_compte_couleur1 = (int) (0);
RDebugUtils.currentLine=589841;
 //BA.debugLineNum = 589841;BA.debugLine="envoie_couleur1=4";
_envoie_couleur1 = (int) (4);
 };
RDebugUtils.currentLine=589843;
 //BA.debugLineNum = 589843;BA.debugLine="End Sub";
return "";
}
public static String  _choix_couleur2_click() throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=524288;
 //BA.debugLineNum = 524288;BA.debugLine="Sub choix_couleur2_Click";
RDebugUtils.currentLine=524289;
 //BA.debugLineNum = 524289;BA.debugLine="compte_couleur2=compte_couleur2+1";
_compte_couleur2 = (int) (_compte_couleur2+1);
RDebugUtils.currentLine=524290;
 //BA.debugLineNum = 524290;BA.debugLine="If compte_couleur2=1 Then";
if (_compte_couleur2==1) { 
RDebugUtils.currentLine=524291;
 //BA.debugLineNum = 524291;BA.debugLine="couleur2.Color=Colors.Red";
mostCurrent._couleur2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
RDebugUtils.currentLine=524292;
 //BA.debugLineNum = 524292;BA.debugLine="envoie_couleur2=1";
_envoie_couleur2 = (int) (1);
 };
RDebugUtils.currentLine=524294;
 //BA.debugLineNum = 524294;BA.debugLine="If compte_couleur2=2 Then";
if (_compte_couleur2==2) { 
RDebugUtils.currentLine=524295;
 //BA.debugLineNum = 524295;BA.debugLine="couleur2.Color=Colors.Blue";
mostCurrent._couleur2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
RDebugUtils.currentLine=524296;
 //BA.debugLineNum = 524296;BA.debugLine="envoie_couleur2=2";
_envoie_couleur2 = (int) (2);
 };
RDebugUtils.currentLine=524298;
 //BA.debugLineNum = 524298;BA.debugLine="If compte_couleur2=3 Then";
if (_compte_couleur2==3) { 
RDebugUtils.currentLine=524299;
 //BA.debugLineNum = 524299;BA.debugLine="couleur2.Color=Colors.Yellow";
mostCurrent._couleur2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
RDebugUtils.currentLine=524300;
 //BA.debugLineNum = 524300;BA.debugLine="envoie_couleur2=3";
_envoie_couleur2 = (int) (3);
 };
RDebugUtils.currentLine=524302;
 //BA.debugLineNum = 524302;BA.debugLine="If compte_couleur2=4 Then";
if (_compte_couleur2==4) { 
RDebugUtils.currentLine=524303;
 //BA.debugLineNum = 524303;BA.debugLine="couleur2.Color=Colors.Green";
mostCurrent._couleur2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
RDebugUtils.currentLine=524304;
 //BA.debugLineNum = 524304;BA.debugLine="compte_couleur2=0";
_compte_couleur2 = (int) (0);
RDebugUtils.currentLine=524305;
 //BA.debugLineNum = 524305;BA.debugLine="envoie_couleur2=4";
_envoie_couleur2 = (int) (4);
 };
RDebugUtils.currentLine=524307;
 //BA.debugLineNum = 524307;BA.debugLine="End Sub";
return "";
}
public static String  _choix_couleur3_click() throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=458752;
 //BA.debugLineNum = 458752;BA.debugLine="Sub choix_couleur3_Click";
RDebugUtils.currentLine=458753;
 //BA.debugLineNum = 458753;BA.debugLine="compte_couleur3=compte_couleur3+1";
_compte_couleur3 = (int) (_compte_couleur3+1);
RDebugUtils.currentLine=458754;
 //BA.debugLineNum = 458754;BA.debugLine="If compte_couleur3=1 Then";
if (_compte_couleur3==1) { 
RDebugUtils.currentLine=458755;
 //BA.debugLineNum = 458755;BA.debugLine="couleur3.Color=Colors.Red";
mostCurrent._couleur3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
RDebugUtils.currentLine=458756;
 //BA.debugLineNum = 458756;BA.debugLine="envoie_couleur3=1";
_envoie_couleur3 = (int) (1);
 };
RDebugUtils.currentLine=458758;
 //BA.debugLineNum = 458758;BA.debugLine="If compte_couleur3=2 Then";
if (_compte_couleur3==2) { 
RDebugUtils.currentLine=458759;
 //BA.debugLineNum = 458759;BA.debugLine="couleur3.Color=Colors.Blue";
mostCurrent._couleur3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
RDebugUtils.currentLine=458760;
 //BA.debugLineNum = 458760;BA.debugLine="envoie_couleur3=2";
_envoie_couleur3 = (int) (2);
 };
RDebugUtils.currentLine=458762;
 //BA.debugLineNum = 458762;BA.debugLine="If compte_couleur3=3 Then";
if (_compte_couleur3==3) { 
RDebugUtils.currentLine=458763;
 //BA.debugLineNum = 458763;BA.debugLine="couleur3.Color=Colors.Yellow";
mostCurrent._couleur3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
RDebugUtils.currentLine=458764;
 //BA.debugLineNum = 458764;BA.debugLine="envoie_couleur3=3";
_envoie_couleur3 = (int) (3);
 };
RDebugUtils.currentLine=458766;
 //BA.debugLineNum = 458766;BA.debugLine="If compte_couleur3=4 Then";
if (_compte_couleur3==4) { 
RDebugUtils.currentLine=458767;
 //BA.debugLineNum = 458767;BA.debugLine="couleur3.Color=Colors.Green";
mostCurrent._couleur3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
RDebugUtils.currentLine=458768;
 //BA.debugLineNum = 458768;BA.debugLine="compte_couleur3=0";
_compte_couleur3 = (int) (0);
RDebugUtils.currentLine=458769;
 //BA.debugLineNum = 458769;BA.debugLine="envoie_couleur3=4";
_envoie_couleur3 = (int) (4);
 };
RDebugUtils.currentLine=458771;
 //BA.debugLineNum = 458771;BA.debugLine="End Sub";
return "";
}
public static String  _choix_couleur4_click() throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=393216;
 //BA.debugLineNum = 393216;BA.debugLine="Sub choix_couleur4_Click";
RDebugUtils.currentLine=393217;
 //BA.debugLineNum = 393217;BA.debugLine="compte_couleur4=compte_couleur4+1";
_compte_couleur4 = (int) (_compte_couleur4+1);
RDebugUtils.currentLine=393218;
 //BA.debugLineNum = 393218;BA.debugLine="If compte_couleur4=1 Then";
if (_compte_couleur4==1) { 
RDebugUtils.currentLine=393219;
 //BA.debugLineNum = 393219;BA.debugLine="couleur4.Color=Colors.Red";
mostCurrent._couleur4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
RDebugUtils.currentLine=393220;
 //BA.debugLineNum = 393220;BA.debugLine="envoie_couleur4=1";
_envoie_couleur4 = (int) (1);
 };
RDebugUtils.currentLine=393222;
 //BA.debugLineNum = 393222;BA.debugLine="If compte_couleur4=2 Then";
if (_compte_couleur4==2) { 
RDebugUtils.currentLine=393223;
 //BA.debugLineNum = 393223;BA.debugLine="couleur4.Color=Colors.Blue";
mostCurrent._couleur4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
RDebugUtils.currentLine=393224;
 //BA.debugLineNum = 393224;BA.debugLine="envoie_couleur4=2";
_envoie_couleur4 = (int) (2);
 };
RDebugUtils.currentLine=393226;
 //BA.debugLineNum = 393226;BA.debugLine="If compte_couleur4=3 Then";
if (_compte_couleur4==3) { 
RDebugUtils.currentLine=393227;
 //BA.debugLineNum = 393227;BA.debugLine="couleur4.Color=Colors.Yellow";
mostCurrent._couleur4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
RDebugUtils.currentLine=393228;
 //BA.debugLineNum = 393228;BA.debugLine="envoie_couleur4=3";
_envoie_couleur4 = (int) (3);
 };
RDebugUtils.currentLine=393230;
 //BA.debugLineNum = 393230;BA.debugLine="If compte_couleur4=4 Then";
if (_compte_couleur4==4) { 
RDebugUtils.currentLine=393231;
 //BA.debugLineNum = 393231;BA.debugLine="couleur4.Color=Colors.Green";
mostCurrent._couleur4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
RDebugUtils.currentLine=393232;
 //BA.debugLineNum = 393232;BA.debugLine="compte_couleur4=0";
_compte_couleur4 = (int) (0);
RDebugUtils.currentLine=393233;
 //BA.debugLineNum = 393233;BA.debugLine="envoie_couleur4=4";
_envoie_couleur4 = (int) (4);
 };
RDebugUtils.currentLine=393235;
 //BA.debugLineNum = 393235;BA.debugLine="End Sub";
return "";
}
public static String  _commencer_click() throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=327680;
 //BA.debugLineNum = 327680;BA.debugLine="Sub Commencer_Click";
RDebugUtils.currentLine=327681;
 //BA.debugLineNum = 327681;BA.debugLine="aleatoire1=Rnd(1,4)";
_aleatoire1 = anywheresoftware.b4a.keywords.Common.Rnd((int) (1),(int) (4));
RDebugUtils.currentLine=327682;
 //BA.debugLineNum = 327682;BA.debugLine="aleatoire2=Rnd(1,4)";
_aleatoire2 = anywheresoftware.b4a.keywords.Common.Rnd((int) (1),(int) (4));
RDebugUtils.currentLine=327683;
 //BA.debugLineNum = 327683;BA.debugLine="aleatoire3=Rnd(1,4)";
_aleatoire3 = anywheresoftware.b4a.keywords.Common.Rnd((int) (1),(int) (4));
RDebugUtils.currentLine=327684;
 //BA.debugLineNum = 327684;BA.debugLine="aleatoire4=Rnd(1,4)";
_aleatoire4 = anywheresoftware.b4a.keywords.Common.Rnd((int) (1),(int) (4));
RDebugUtils.currentLine=327685;
 //BA.debugLineNum = 327685;BA.debugLine="Resulat.Color=Colors.Black";
mostCurrent._resulat.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=327686;
 //BA.debugLineNum = 327686;BA.debugLine="Resulat.TextColor=Colors.White";
mostCurrent._resulat.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=327687;
 //BA.debugLineNum = 327687;BA.debugLine="Resulat.Text=\"Choisir les couleurs : Rouge, Bleu,";
mostCurrent._resulat.setText((Object)("Choisir les couleurs : Rouge, Bleu, Verte et Jaune"));
RDebugUtils.currentLine=327688;
 //BA.debugLineNum = 327688;BA.debugLine="End Sub";
return "";
}
public static String  _comparaison() throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=720896;
 //BA.debugLineNum = 720896;BA.debugLine="Sub comparaison";
RDebugUtils.currentLine=720897;
 //BA.debugLineNum = 720897;BA.debugLine="If envoie_couleur1=aleatoire1 Then";
if (_envoie_couleur1==_aleatoire1) { 
RDebugUtils.currentLine=720898;
 //BA.debugLineNum = 720898;BA.debugLine="Bonne_place1=1";
_bonne_place1 = (int) (1);
RDebugUtils.currentLine=720899;
 //BA.debugLineNum = 720899;BA.debugLine="pion1.Color=Colors.Black";
mostCurrent._pion1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 }else 
{RDebugUtils.currentLine=720900;
 //BA.debugLineNum = 720900;BA.debugLine="Else If envoie_couleur1=0 Then";
if (_envoie_couleur1==0) { 
RDebugUtils.currentLine=720901;
 //BA.debugLineNum = 720901;BA.debugLine="Bonne_place1=0";
_bonne_place1 = (int) (0);
RDebugUtils.currentLine=720902;
 //BA.debugLineNum = 720902;BA.debugLine="pion1.Color=Colors.White";
mostCurrent._pion1.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=720904;
 //BA.debugLineNum = 720904;BA.debugLine="Bonne_place1=0";
_bonne_place1 = (int) (0);
RDebugUtils.currentLine=720905;
 //BA.debugLineNum = 720905;BA.debugLine="pion1.Color=Colors.White";
mostCurrent._pion1.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }};
RDebugUtils.currentLine=720908;
 //BA.debugLineNum = 720908;BA.debugLine="If envoie_couleur2=aleatoire2 Then";
if (_envoie_couleur2==_aleatoire2) { 
RDebugUtils.currentLine=720909;
 //BA.debugLineNum = 720909;BA.debugLine="Bonne_place2=1";
_bonne_place2 = (int) (1);
RDebugUtils.currentLine=720910;
 //BA.debugLineNum = 720910;BA.debugLine="pion2.Color=Colors.Black";
mostCurrent._pion2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 }else 
{RDebugUtils.currentLine=720911;
 //BA.debugLineNum = 720911;BA.debugLine="Else If envoie_couleur2=0 Then";
if (_envoie_couleur2==0) { 
RDebugUtils.currentLine=720912;
 //BA.debugLineNum = 720912;BA.debugLine="Bonne_place2=0";
_bonne_place2 = (int) (0);
RDebugUtils.currentLine=720913;
 //BA.debugLineNum = 720913;BA.debugLine="pion2.Color=Colors.White";
mostCurrent._pion2.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=720915;
 //BA.debugLineNum = 720915;BA.debugLine="Bonne_place2=0";
_bonne_place2 = (int) (0);
RDebugUtils.currentLine=720916;
 //BA.debugLineNum = 720916;BA.debugLine="pion2.Color=Colors.White";
mostCurrent._pion2.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }};
RDebugUtils.currentLine=720919;
 //BA.debugLineNum = 720919;BA.debugLine="If envoie_couleur3=aleatoire3 Then";
if (_envoie_couleur3==_aleatoire3) { 
RDebugUtils.currentLine=720920;
 //BA.debugLineNum = 720920;BA.debugLine="Bonne_place3=1";
_bonne_place3 = (int) (1);
RDebugUtils.currentLine=720921;
 //BA.debugLineNum = 720921;BA.debugLine="pion3.Color=Colors.Black";
mostCurrent._pion3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 }else 
{RDebugUtils.currentLine=720922;
 //BA.debugLineNum = 720922;BA.debugLine="Else If envoie_couleur3=0 Then";
if (_envoie_couleur3==0) { 
RDebugUtils.currentLine=720923;
 //BA.debugLineNum = 720923;BA.debugLine="Bonne_place3=0";
_bonne_place3 = (int) (0);
RDebugUtils.currentLine=720924;
 //BA.debugLineNum = 720924;BA.debugLine="pion3.Color=Colors.White";
mostCurrent._pion3.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=720926;
 //BA.debugLineNum = 720926;BA.debugLine="Bonne_place3=0";
_bonne_place3 = (int) (0);
RDebugUtils.currentLine=720927;
 //BA.debugLineNum = 720927;BA.debugLine="pion3.Color=Colors.White";
mostCurrent._pion3.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }};
RDebugUtils.currentLine=720930;
 //BA.debugLineNum = 720930;BA.debugLine="If envoie_couleur4=aleatoire4 Then";
if (_envoie_couleur4==_aleatoire4) { 
RDebugUtils.currentLine=720931;
 //BA.debugLineNum = 720931;BA.debugLine="Bonne_place4=1";
_bonne_place4 = (int) (1);
RDebugUtils.currentLine=720932;
 //BA.debugLineNum = 720932;BA.debugLine="pion4.Color=Colors.Black";
mostCurrent._pion4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 }else 
{RDebugUtils.currentLine=720933;
 //BA.debugLineNum = 720933;BA.debugLine="Else If envoie_couleur4=0 Then";
if (_envoie_couleur4==0) { 
RDebugUtils.currentLine=720934;
 //BA.debugLineNum = 720934;BA.debugLine="Bonne_place4=0";
_bonne_place4 = (int) (0);
RDebugUtils.currentLine=720935;
 //BA.debugLineNum = 720935;BA.debugLine="pion4.Color=Colors.White";
mostCurrent._pion4.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
RDebugUtils.currentLine=720937;
 //BA.debugLineNum = 720937;BA.debugLine="Bonne_place4=0";
_bonne_place4 = (int) (0);
RDebugUtils.currentLine=720938;
 //BA.debugLineNum = 720938;BA.debugLine="pion4.Color=Colors.White";
mostCurrent._pion4.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }};
RDebugUtils.currentLine=720941;
 //BA.debugLineNum = 720941;BA.debugLine="If Bonne_place1=1 And Bonne_place2=1 And Bonne_pl";
if (_bonne_place1==1 && _bonne_place2==1 && _bonne_place3==1 && _bonne_place4==1) { 
RDebugUtils.currentLine=720942;
 //BA.debugLineNum = 720942;BA.debugLine="Resulat.Color=Colors.Black";
mostCurrent._resulat.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
RDebugUtils.currentLine=720943;
 //BA.debugLineNum = 720943;BA.debugLine="Resulat.TextColor=Colors.White";
mostCurrent._resulat.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
RDebugUtils.currentLine=720944;
 //BA.debugLineNum = 720944;BA.debugLine="Resulat.Text=\"Bravo tu as gagné ! \"";
mostCurrent._resulat.setText((Object)("Bravo tu as gagné ! "));
 };
RDebugUtils.currentLine=720947;
 //BA.debugLineNum = 720947;BA.debugLine="End Sub";
return "";
}
public static String  _debug_click() throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=786432;
 //BA.debugLineNum = 786432;BA.debugLine="Sub Debug_Click";
RDebugUtils.currentLine=786433;
 //BA.debugLineNum = 786433;BA.debugLine="If aleatoire1=1 Then";
if (_aleatoire1==1) { 
RDebugUtils.currentLine=786434;
 //BA.debugLineNum = 786434;BA.debugLine="couleur_aleatoire1.Color=Colors.Red";
mostCurrent._couleur_aleatoire1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
RDebugUtils.currentLine=786435;
 //BA.debugLineNum = 786435;BA.debugLine="couleur_aleatoire1.Text=aleatoire1";
mostCurrent._couleur_aleatoire1.setText((Object)(_aleatoire1));
 };
RDebugUtils.currentLine=786437;
 //BA.debugLineNum = 786437;BA.debugLine="If aleatoire1=2 Then";
if (_aleatoire1==2) { 
RDebugUtils.currentLine=786438;
 //BA.debugLineNum = 786438;BA.debugLine="couleur_aleatoire1.Color=Colors.Blue";
mostCurrent._couleur_aleatoire1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
RDebugUtils.currentLine=786439;
 //BA.debugLineNum = 786439;BA.debugLine="couleur_aleatoire1.Text=aleatoire1";
mostCurrent._couleur_aleatoire1.setText((Object)(_aleatoire1));
 };
RDebugUtils.currentLine=786441;
 //BA.debugLineNum = 786441;BA.debugLine="If aleatoire1=3 Then";
if (_aleatoire1==3) { 
RDebugUtils.currentLine=786442;
 //BA.debugLineNum = 786442;BA.debugLine="couleur_aleatoire1.Color=Colors.Yellow";
mostCurrent._couleur_aleatoire1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
RDebugUtils.currentLine=786443;
 //BA.debugLineNum = 786443;BA.debugLine="couleur_aleatoire1.Text=aleatoire1";
mostCurrent._couleur_aleatoire1.setText((Object)(_aleatoire1));
 };
RDebugUtils.currentLine=786445;
 //BA.debugLineNum = 786445;BA.debugLine="If aleatoire1=4 Then";
if (_aleatoire1==4) { 
RDebugUtils.currentLine=786446;
 //BA.debugLineNum = 786446;BA.debugLine="couleur_aleatoire1.Color=Colors.Green";
mostCurrent._couleur_aleatoire1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
RDebugUtils.currentLine=786447;
 //BA.debugLineNum = 786447;BA.debugLine="couleur_aleatoire1.Text=aleatoire1";
mostCurrent._couleur_aleatoire1.setText((Object)(_aleatoire1));
 };
RDebugUtils.currentLine=786450;
 //BA.debugLineNum = 786450;BA.debugLine="If aleatoire2=1 Then";
if (_aleatoire2==1) { 
RDebugUtils.currentLine=786451;
 //BA.debugLineNum = 786451;BA.debugLine="couleur_aleatoire2.Color=Colors.Red";
mostCurrent._couleur_aleatoire2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
RDebugUtils.currentLine=786452;
 //BA.debugLineNum = 786452;BA.debugLine="couleur_aleatoire2.Text=aleatoire2";
mostCurrent._couleur_aleatoire2.setText((Object)(_aleatoire2));
 };
RDebugUtils.currentLine=786454;
 //BA.debugLineNum = 786454;BA.debugLine="If aleatoire2=2 Then";
if (_aleatoire2==2) { 
RDebugUtils.currentLine=786455;
 //BA.debugLineNum = 786455;BA.debugLine="couleur_aleatoire2.Color=Colors.Blue";
mostCurrent._couleur_aleatoire2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
RDebugUtils.currentLine=786456;
 //BA.debugLineNum = 786456;BA.debugLine="couleur_aleatoire2.Text=aleatoire2";
mostCurrent._couleur_aleatoire2.setText((Object)(_aleatoire2));
 };
RDebugUtils.currentLine=786458;
 //BA.debugLineNum = 786458;BA.debugLine="If aleatoire2=3 Then";
if (_aleatoire2==3) { 
RDebugUtils.currentLine=786459;
 //BA.debugLineNum = 786459;BA.debugLine="couleur_aleatoire2.Color=Colors.Yellow";
mostCurrent._couleur_aleatoire2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
RDebugUtils.currentLine=786460;
 //BA.debugLineNum = 786460;BA.debugLine="couleur_aleatoire2.Text=aleatoire2";
mostCurrent._couleur_aleatoire2.setText((Object)(_aleatoire2));
 };
RDebugUtils.currentLine=786462;
 //BA.debugLineNum = 786462;BA.debugLine="If aleatoire2=4 Then";
if (_aleatoire2==4) { 
RDebugUtils.currentLine=786463;
 //BA.debugLineNum = 786463;BA.debugLine="couleur_aleatoire2.Color=Colors.Green";
mostCurrent._couleur_aleatoire2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
RDebugUtils.currentLine=786464;
 //BA.debugLineNum = 786464;BA.debugLine="couleur_aleatoire2.Text=aleatoire2";
mostCurrent._couleur_aleatoire2.setText((Object)(_aleatoire2));
 };
RDebugUtils.currentLine=786467;
 //BA.debugLineNum = 786467;BA.debugLine="If aleatoire3=1 Then";
if (_aleatoire3==1) { 
RDebugUtils.currentLine=786468;
 //BA.debugLineNum = 786468;BA.debugLine="couleur_aleatoire3.Color=Colors.Red";
mostCurrent._couleur_aleatoire3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
RDebugUtils.currentLine=786469;
 //BA.debugLineNum = 786469;BA.debugLine="couleur_aleatoire3.Text=aleatoire3";
mostCurrent._couleur_aleatoire3.setText((Object)(_aleatoire3));
 };
RDebugUtils.currentLine=786471;
 //BA.debugLineNum = 786471;BA.debugLine="If aleatoire3=2 Then";
if (_aleatoire3==2) { 
RDebugUtils.currentLine=786472;
 //BA.debugLineNum = 786472;BA.debugLine="couleur_aleatoire3.Color=Colors.Blue";
mostCurrent._couleur_aleatoire3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
RDebugUtils.currentLine=786473;
 //BA.debugLineNum = 786473;BA.debugLine="couleur_aleatoire3.Text=aleatoire3";
mostCurrent._couleur_aleatoire3.setText((Object)(_aleatoire3));
 };
RDebugUtils.currentLine=786475;
 //BA.debugLineNum = 786475;BA.debugLine="If aleatoire3=3 Then";
if (_aleatoire3==3) { 
RDebugUtils.currentLine=786476;
 //BA.debugLineNum = 786476;BA.debugLine="couleur_aleatoire3.Color=Colors.Yellow";
mostCurrent._couleur_aleatoire3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
RDebugUtils.currentLine=786477;
 //BA.debugLineNum = 786477;BA.debugLine="couleur_aleatoire3.Text=aleatoire3";
mostCurrent._couleur_aleatoire3.setText((Object)(_aleatoire3));
 };
RDebugUtils.currentLine=786479;
 //BA.debugLineNum = 786479;BA.debugLine="If aleatoire3=4 Then";
if (_aleatoire3==4) { 
RDebugUtils.currentLine=786480;
 //BA.debugLineNum = 786480;BA.debugLine="couleur_aleatoire3.Color=Colors.Green";
mostCurrent._couleur_aleatoire3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
RDebugUtils.currentLine=786481;
 //BA.debugLineNum = 786481;BA.debugLine="couleur_aleatoire3.Text=aleatoire3";
mostCurrent._couleur_aleatoire3.setText((Object)(_aleatoire3));
 };
RDebugUtils.currentLine=786484;
 //BA.debugLineNum = 786484;BA.debugLine="If aleatoire4=1 Then";
if (_aleatoire4==1) { 
RDebugUtils.currentLine=786485;
 //BA.debugLineNum = 786485;BA.debugLine="couleur_aleatoire4.Color=Colors.Red";
mostCurrent._couleur_aleatoire4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
RDebugUtils.currentLine=786486;
 //BA.debugLineNum = 786486;BA.debugLine="couleur_aleatoire4.Text=aleatoire4";
mostCurrent._couleur_aleatoire4.setText((Object)(_aleatoire4));
 };
RDebugUtils.currentLine=786488;
 //BA.debugLineNum = 786488;BA.debugLine="If aleatoire4=2 Then";
if (_aleatoire4==2) { 
RDebugUtils.currentLine=786489;
 //BA.debugLineNum = 786489;BA.debugLine="couleur_aleatoire4.Color=Colors.Blue";
mostCurrent._couleur_aleatoire4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
RDebugUtils.currentLine=786490;
 //BA.debugLineNum = 786490;BA.debugLine="couleur_aleatoire4.Text=aleatoire4";
mostCurrent._couleur_aleatoire4.setText((Object)(_aleatoire4));
 };
RDebugUtils.currentLine=786492;
 //BA.debugLineNum = 786492;BA.debugLine="If aleatoire3=3 Then";
if (_aleatoire3==3) { 
RDebugUtils.currentLine=786493;
 //BA.debugLineNum = 786493;BA.debugLine="couleur_aleatoire4.Color=Colors.Yellow";
mostCurrent._couleur_aleatoire4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
RDebugUtils.currentLine=786494;
 //BA.debugLineNum = 786494;BA.debugLine="couleur_aleatoire4.Text=aleatoire4";
mostCurrent._couleur_aleatoire4.setText((Object)(_aleatoire4));
 };
RDebugUtils.currentLine=786496;
 //BA.debugLineNum = 786496;BA.debugLine="If aleatoire3=4 Then";
if (_aleatoire3==4) { 
RDebugUtils.currentLine=786497;
 //BA.debugLineNum = 786497;BA.debugLine="couleur_aleatoire4.Color=Colors.Green";
mostCurrent._couleur_aleatoire4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
RDebugUtils.currentLine=786498;
 //BA.debugLineNum = 786498;BA.debugLine="couleur_aleatoire4.Text=aleatoire4";
mostCurrent._couleur_aleatoire4.setText((Object)(_aleatoire4));
 };
RDebugUtils.currentLine=786500;
 //BA.debugLineNum = 786500;BA.debugLine="End Sub";
return "";
}
public static String  _envoyer_click() throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=655360;
 //BA.debugLineNum = 655360;BA.debugLine="Sub Envoyer_Click";
RDebugUtils.currentLine=655361;
 //BA.debugLineNum = 655361;BA.debugLine="If envoie_couleur1=1 Then";
if (_envoie_couleur1==1) { 
RDebugUtils.currentLine=655362;
 //BA.debugLineNum = 655362;BA.debugLine="afficher_couleur1.Color=Colors.Red";
mostCurrent._afficher_couleur1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 };
RDebugUtils.currentLine=655364;
 //BA.debugLineNum = 655364;BA.debugLine="If envoie_couleur1=2 Then";
if (_envoie_couleur1==2) { 
RDebugUtils.currentLine=655365;
 //BA.debugLineNum = 655365;BA.debugLine="afficher_couleur1.Color=Colors.Blue";
mostCurrent._afficher_couleur1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
 };
RDebugUtils.currentLine=655367;
 //BA.debugLineNum = 655367;BA.debugLine="If envoie_couleur1=3 Then";
if (_envoie_couleur1==3) { 
RDebugUtils.currentLine=655368;
 //BA.debugLineNum = 655368;BA.debugLine="afficher_couleur1.Color=Colors.Yellow";
mostCurrent._afficher_couleur1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 };
RDebugUtils.currentLine=655370;
 //BA.debugLineNum = 655370;BA.debugLine="If envoie_couleur1=4 Then";
if (_envoie_couleur1==4) { 
RDebugUtils.currentLine=655371;
 //BA.debugLineNum = 655371;BA.debugLine="afficher_couleur1.Color=Colors.Green";
mostCurrent._afficher_couleur1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
 };
RDebugUtils.currentLine=655374;
 //BA.debugLineNum = 655374;BA.debugLine="If envoie_couleur2=1 Then";
if (_envoie_couleur2==1) { 
RDebugUtils.currentLine=655375;
 //BA.debugLineNum = 655375;BA.debugLine="afficher_couleur2.Color=Colors.Red";
mostCurrent._afficher_couleur2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 };
RDebugUtils.currentLine=655377;
 //BA.debugLineNum = 655377;BA.debugLine="If envoie_couleur2=2 Then";
if (_envoie_couleur2==2) { 
RDebugUtils.currentLine=655378;
 //BA.debugLineNum = 655378;BA.debugLine="afficher_couleur2.Color=Colors.Blue";
mostCurrent._afficher_couleur2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
 };
RDebugUtils.currentLine=655380;
 //BA.debugLineNum = 655380;BA.debugLine="If envoie_couleur2=3 Then";
if (_envoie_couleur2==3) { 
RDebugUtils.currentLine=655381;
 //BA.debugLineNum = 655381;BA.debugLine="afficher_couleur2.Color=Colors.Yellow";
mostCurrent._afficher_couleur2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 };
RDebugUtils.currentLine=655383;
 //BA.debugLineNum = 655383;BA.debugLine="If envoie_couleur2=4 Then";
if (_envoie_couleur2==4) { 
RDebugUtils.currentLine=655384;
 //BA.debugLineNum = 655384;BA.debugLine="afficher_couleur2.Color=Colors.Green";
mostCurrent._afficher_couleur2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
 };
RDebugUtils.currentLine=655387;
 //BA.debugLineNum = 655387;BA.debugLine="If envoie_couleur3=1 Then";
if (_envoie_couleur3==1) { 
RDebugUtils.currentLine=655388;
 //BA.debugLineNum = 655388;BA.debugLine="afficher_couleur3.Color=Colors.Red";
mostCurrent._afficher_couleur3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 };
RDebugUtils.currentLine=655390;
 //BA.debugLineNum = 655390;BA.debugLine="If envoie_couleur3=2 Then";
if (_envoie_couleur3==2) { 
RDebugUtils.currentLine=655391;
 //BA.debugLineNum = 655391;BA.debugLine="afficher_couleur3.Color=Colors.Blue";
mostCurrent._afficher_couleur3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
 };
RDebugUtils.currentLine=655393;
 //BA.debugLineNum = 655393;BA.debugLine="If envoie_couleur3=3 Then";
if (_envoie_couleur3==3) { 
RDebugUtils.currentLine=655394;
 //BA.debugLineNum = 655394;BA.debugLine="afficher_couleur3.Color=Colors.Yellow";
mostCurrent._afficher_couleur3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 };
RDebugUtils.currentLine=655396;
 //BA.debugLineNum = 655396;BA.debugLine="If envoie_couleur3=4 Then";
if (_envoie_couleur3==4) { 
RDebugUtils.currentLine=655397;
 //BA.debugLineNum = 655397;BA.debugLine="afficher_couleur3.Color=Colors.Green";
mostCurrent._afficher_couleur3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
 };
RDebugUtils.currentLine=655400;
 //BA.debugLineNum = 655400;BA.debugLine="If envoie_couleur4=1 Then";
if (_envoie_couleur4==1) { 
RDebugUtils.currentLine=655401;
 //BA.debugLineNum = 655401;BA.debugLine="afficher_couleur4.Color=Colors.Red";
mostCurrent._afficher_couleur4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 };
RDebugUtils.currentLine=655403;
 //BA.debugLineNum = 655403;BA.debugLine="If envoie_couleur4=2 Then";
if (_envoie_couleur4==2) { 
RDebugUtils.currentLine=655404;
 //BA.debugLineNum = 655404;BA.debugLine="afficher_couleur4.Color=Colors.Blue";
mostCurrent._afficher_couleur4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
 };
RDebugUtils.currentLine=655406;
 //BA.debugLineNum = 655406;BA.debugLine="If envoie_couleur4=3 Then";
if (_envoie_couleur4==3) { 
RDebugUtils.currentLine=655407;
 //BA.debugLineNum = 655407;BA.debugLine="afficher_couleur4.Color=Colors.Yellow";
mostCurrent._afficher_couleur4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 };
RDebugUtils.currentLine=655409;
 //BA.debugLineNum = 655409;BA.debugLine="If envoie_couleur4=4 Then";
if (_envoie_couleur4==4) { 
RDebugUtils.currentLine=655410;
 //BA.debugLineNum = 655410;BA.debugLine="afficher_couleur4.Color=Colors.Green";
mostCurrent._afficher_couleur4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
 };
RDebugUtils.currentLine=655412;
 //BA.debugLineNum = 655412;BA.debugLine="comparaison";
_comparaison();
RDebugUtils.currentLine=655413;
 //BA.debugLineNum = 655413;BA.debugLine="End Sub";
return "";
}
}