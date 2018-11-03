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
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.main");
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
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
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
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
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
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
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
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        Object[] o;
        if (permissions.length > 0)
            o = new Object[] {permissions[0], grantResults[0] == 0};
        else
            o = new Object[] {"", false};
        processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
            
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

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 63;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 67;BA.debugLine="compte_couleur1=0";
_compte_couleur1 = (int) (0);
 //BA.debugLineNum = 68;BA.debugLine="compte_couleur2=0";
_compte_couleur2 = (int) (0);
 //BA.debugLineNum = 69;BA.debugLine="compte_couleur3=0";
_compte_couleur3 = (int) (0);
 //BA.debugLineNum = 70;BA.debugLine="compte_couleur4=0";
_compte_couleur4 = (int) (0);
 //BA.debugLineNum = 71;BA.debugLine="envoie_couleur1=0";
_envoie_couleur1 = (int) (0);
 //BA.debugLineNum = 72;BA.debugLine="envoie_couleur2=0";
_envoie_couleur2 = (int) (0);
 //BA.debugLineNum = 73;BA.debugLine="envoie_couleur3=0";
_envoie_couleur3 = (int) (0);
 //BA.debugLineNum = 74;BA.debugLine="envoie_couleur4=0";
_envoie_couleur4 = (int) (0);
 //BA.debugLineNum = 75;BA.debugLine="Fond.Color=Colors.White";
mostCurrent._fond.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 76;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 82;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 84;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 78;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 80;BA.debugLine="End Sub";
return "";
}
public static String  _choix_couleur1_click() throws Exception{
 //BA.debugLineNum = 159;BA.debugLine="Sub choix_couleur1_Click";
 //BA.debugLineNum = 160;BA.debugLine="compte_couleur1=compte_couleur1+1";
_compte_couleur1 = (int) (_compte_couleur1+1);
 //BA.debugLineNum = 161;BA.debugLine="If compte_couleur1=1 Then";
if (_compte_couleur1==1) { 
 //BA.debugLineNum = 162;BA.debugLine="couleur1.Color=Colors.Red";
mostCurrent._couleur1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 163;BA.debugLine="envoie_couleur1=1";
_envoie_couleur1 = (int) (1);
 };
 //BA.debugLineNum = 165;BA.debugLine="If compte_couleur1=2 Then";
if (_compte_couleur1==2) { 
 //BA.debugLineNum = 166;BA.debugLine="couleur1.Color=Colors.Blue";
mostCurrent._couleur1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 167;BA.debugLine="envoie_couleur1=2";
_envoie_couleur1 = (int) (2);
 };
 //BA.debugLineNum = 169;BA.debugLine="If compte_couleur1=3 Then";
if (_compte_couleur1==3) { 
 //BA.debugLineNum = 170;BA.debugLine="couleur1.Color=Colors.Yellow";
mostCurrent._couleur1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 171;BA.debugLine="envoie_couleur1=3";
_envoie_couleur1 = (int) (3);
 };
 //BA.debugLineNum = 173;BA.debugLine="If compte_couleur1=4 Then";
if (_compte_couleur1==4) { 
 //BA.debugLineNum = 174;BA.debugLine="couleur1.Color=Colors.Green";
mostCurrent._couleur1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
 //BA.debugLineNum = 175;BA.debugLine="compte_couleur1=0";
_compte_couleur1 = (int) (0);
 //BA.debugLineNum = 176;BA.debugLine="envoie_couleur1=4";
_envoie_couleur1 = (int) (4);
 };
 //BA.debugLineNum = 178;BA.debugLine="End Sub";
return "";
}
public static String  _choix_couleur2_click() throws Exception{
 //BA.debugLineNum = 138;BA.debugLine="Sub choix_couleur2_Click";
 //BA.debugLineNum = 139;BA.debugLine="compte_couleur2=compte_couleur2+1";
_compte_couleur2 = (int) (_compte_couleur2+1);
 //BA.debugLineNum = 140;BA.debugLine="If compte_couleur2=1 Then";
if (_compte_couleur2==1) { 
 //BA.debugLineNum = 141;BA.debugLine="couleur2.Color=Colors.Red";
mostCurrent._couleur2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 142;BA.debugLine="envoie_couleur2=1";
_envoie_couleur2 = (int) (1);
 };
 //BA.debugLineNum = 144;BA.debugLine="If compte_couleur2=2 Then";
if (_compte_couleur2==2) { 
 //BA.debugLineNum = 145;BA.debugLine="couleur2.Color=Colors.Blue";
mostCurrent._couleur2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 146;BA.debugLine="envoie_couleur2=2";
_envoie_couleur2 = (int) (2);
 };
 //BA.debugLineNum = 148;BA.debugLine="If compte_couleur2=3 Then";
if (_compte_couleur2==3) { 
 //BA.debugLineNum = 149;BA.debugLine="couleur2.Color=Colors.Yellow";
mostCurrent._couleur2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 150;BA.debugLine="envoie_couleur2=3";
_envoie_couleur2 = (int) (3);
 };
 //BA.debugLineNum = 152;BA.debugLine="If compte_couleur2=4 Then";
if (_compte_couleur2==4) { 
 //BA.debugLineNum = 153;BA.debugLine="couleur2.Color=Colors.Green";
mostCurrent._couleur2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
 //BA.debugLineNum = 154;BA.debugLine="compte_couleur2=0";
_compte_couleur2 = (int) (0);
 //BA.debugLineNum = 155;BA.debugLine="envoie_couleur2=4";
_envoie_couleur2 = (int) (4);
 };
 //BA.debugLineNum = 157;BA.debugLine="End Sub";
return "";
}
public static String  _choix_couleur3_click() throws Exception{
 //BA.debugLineNum = 117;BA.debugLine="Sub choix_couleur3_Click";
 //BA.debugLineNum = 118;BA.debugLine="compte_couleur3=compte_couleur3+1";
_compte_couleur3 = (int) (_compte_couleur3+1);
 //BA.debugLineNum = 119;BA.debugLine="If compte_couleur3=1 Then";
if (_compte_couleur3==1) { 
 //BA.debugLineNum = 120;BA.debugLine="couleur3.Color=Colors.Red";
mostCurrent._couleur3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 121;BA.debugLine="envoie_couleur3=1";
_envoie_couleur3 = (int) (1);
 };
 //BA.debugLineNum = 123;BA.debugLine="If compte_couleur3=2 Then";
if (_compte_couleur3==2) { 
 //BA.debugLineNum = 124;BA.debugLine="couleur3.Color=Colors.Blue";
mostCurrent._couleur3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 125;BA.debugLine="envoie_couleur3=2";
_envoie_couleur3 = (int) (2);
 };
 //BA.debugLineNum = 127;BA.debugLine="If compte_couleur3=3 Then";
if (_compte_couleur3==3) { 
 //BA.debugLineNum = 128;BA.debugLine="couleur3.Color=Colors.Yellow";
mostCurrent._couleur3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 129;BA.debugLine="envoie_couleur3=3";
_envoie_couleur3 = (int) (3);
 };
 //BA.debugLineNum = 131;BA.debugLine="If compte_couleur3=4 Then";
if (_compte_couleur3==4) { 
 //BA.debugLineNum = 132;BA.debugLine="couleur3.Color=Colors.Green";
mostCurrent._couleur3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
 //BA.debugLineNum = 133;BA.debugLine="compte_couleur3=0";
_compte_couleur3 = (int) (0);
 //BA.debugLineNum = 134;BA.debugLine="envoie_couleur3=4";
_envoie_couleur3 = (int) (4);
 };
 //BA.debugLineNum = 136;BA.debugLine="End Sub";
return "";
}
public static String  _choix_couleur4_click() throws Exception{
 //BA.debugLineNum = 96;BA.debugLine="Sub choix_couleur4_Click";
 //BA.debugLineNum = 97;BA.debugLine="compte_couleur4=compte_couleur4+1";
_compte_couleur4 = (int) (_compte_couleur4+1);
 //BA.debugLineNum = 98;BA.debugLine="If compte_couleur4=1 Then";
if (_compte_couleur4==1) { 
 //BA.debugLineNum = 99;BA.debugLine="couleur4.Color=Colors.Red";
mostCurrent._couleur4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 100;BA.debugLine="envoie_couleur4=1";
_envoie_couleur4 = (int) (1);
 };
 //BA.debugLineNum = 102;BA.debugLine="If compte_couleur4=2 Then";
if (_compte_couleur4==2) { 
 //BA.debugLineNum = 103;BA.debugLine="couleur4.Color=Colors.Blue";
mostCurrent._couleur4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 104;BA.debugLine="envoie_couleur4=2";
_envoie_couleur4 = (int) (2);
 };
 //BA.debugLineNum = 106;BA.debugLine="If compte_couleur4=3 Then";
if (_compte_couleur4==3) { 
 //BA.debugLineNum = 107;BA.debugLine="couleur4.Color=Colors.Yellow";
mostCurrent._couleur4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 108;BA.debugLine="envoie_couleur4=3";
_envoie_couleur4 = (int) (3);
 };
 //BA.debugLineNum = 110;BA.debugLine="If compte_couleur4=4 Then";
if (_compte_couleur4==4) { 
 //BA.debugLineNum = 111;BA.debugLine="couleur4.Color=Colors.Green";
mostCurrent._couleur4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
 //BA.debugLineNum = 112;BA.debugLine="compte_couleur4=0";
_compte_couleur4 = (int) (0);
 //BA.debugLineNum = 113;BA.debugLine="envoie_couleur4=4";
_envoie_couleur4 = (int) (4);
 };
 //BA.debugLineNum = 115;BA.debugLine="End Sub";
return "";
}
public static String  _commencer_click() throws Exception{
 //BA.debugLineNum = 86;BA.debugLine="Sub Commencer_Click";
 //BA.debugLineNum = 87;BA.debugLine="aleatoire1=Rnd(1,4)";
_aleatoire1 = anywheresoftware.b4a.keywords.Common.Rnd((int) (1),(int) (4));
 //BA.debugLineNum = 88;BA.debugLine="aleatoire2=Rnd(1,4)";
_aleatoire2 = anywheresoftware.b4a.keywords.Common.Rnd((int) (1),(int) (4));
 //BA.debugLineNum = 89;BA.debugLine="aleatoire3=Rnd(1,4)";
_aleatoire3 = anywheresoftware.b4a.keywords.Common.Rnd((int) (1),(int) (4));
 //BA.debugLineNum = 90;BA.debugLine="aleatoire4=Rnd(1,4)";
_aleatoire4 = anywheresoftware.b4a.keywords.Common.Rnd((int) (1),(int) (4));
 //BA.debugLineNum = 91;BA.debugLine="Resulat.Color=Colors.Black";
mostCurrent._resulat.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 92;BA.debugLine="Resulat.TextColor=Colors.White";
mostCurrent._resulat.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 93;BA.debugLine="Resulat.Text=\"Choisir les couleurs : Rouge, Bleu,";
mostCurrent._resulat.setText((Object)("Choisir les couleurs : Rouge, Bleu, Verte et Jaune"));
 //BA.debugLineNum = 94;BA.debugLine="End Sub";
return "";
}
public static String  _comparaison() throws Exception{
 //BA.debugLineNum = 235;BA.debugLine="Sub comparaison";
 //BA.debugLineNum = 236;BA.debugLine="If envoie_couleur1=aleatoire1 Then";
if (_envoie_couleur1==_aleatoire1) { 
 //BA.debugLineNum = 237;BA.debugLine="Bonne_place1=1";
_bonne_place1 = (int) (1);
 //BA.debugLineNum = 238;BA.debugLine="pion1.Color=Colors.Black";
mostCurrent._pion1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 }else if(_envoie_couleur1==0) { 
 //BA.debugLineNum = 240;BA.debugLine="Bonne_place1=0";
_bonne_place1 = (int) (0);
 //BA.debugLineNum = 241;BA.debugLine="pion1.Color=Colors.White";
mostCurrent._pion1.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
 //BA.debugLineNum = 243;BA.debugLine="Bonne_place1=0";
_bonne_place1 = (int) (0);
 //BA.debugLineNum = 244;BA.debugLine="pion1.Color=Colors.White";
mostCurrent._pion1.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 //BA.debugLineNum = 247;BA.debugLine="If envoie_couleur2=aleatoire2 Then";
if (_envoie_couleur2==_aleatoire2) { 
 //BA.debugLineNum = 248;BA.debugLine="Bonne_place2=1";
_bonne_place2 = (int) (1);
 //BA.debugLineNum = 249;BA.debugLine="pion2.Color=Colors.Black";
mostCurrent._pion2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 }else if(_envoie_couleur2==0) { 
 //BA.debugLineNum = 251;BA.debugLine="Bonne_place2=0";
_bonne_place2 = (int) (0);
 //BA.debugLineNum = 252;BA.debugLine="pion2.Color=Colors.White";
mostCurrent._pion2.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
 //BA.debugLineNum = 254;BA.debugLine="Bonne_place2=0";
_bonne_place2 = (int) (0);
 //BA.debugLineNum = 255;BA.debugLine="pion2.Color=Colors.White";
mostCurrent._pion2.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 //BA.debugLineNum = 258;BA.debugLine="If envoie_couleur3=aleatoire3 Then";
if (_envoie_couleur3==_aleatoire3) { 
 //BA.debugLineNum = 259;BA.debugLine="Bonne_place3=1";
_bonne_place3 = (int) (1);
 //BA.debugLineNum = 260;BA.debugLine="pion3.Color=Colors.Black";
mostCurrent._pion3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 }else if(_envoie_couleur3==0) { 
 //BA.debugLineNum = 262;BA.debugLine="Bonne_place3=0";
_bonne_place3 = (int) (0);
 //BA.debugLineNum = 263;BA.debugLine="pion3.Color=Colors.White";
mostCurrent._pion3.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
 //BA.debugLineNum = 265;BA.debugLine="Bonne_place3=0";
_bonne_place3 = (int) (0);
 //BA.debugLineNum = 266;BA.debugLine="pion3.Color=Colors.White";
mostCurrent._pion3.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 //BA.debugLineNum = 269;BA.debugLine="If envoie_couleur4=aleatoire4 Then";
if (_envoie_couleur4==_aleatoire4) { 
 //BA.debugLineNum = 270;BA.debugLine="Bonne_place4=1";
_bonne_place4 = (int) (1);
 //BA.debugLineNum = 271;BA.debugLine="pion4.Color=Colors.Black";
mostCurrent._pion4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 }else if(_envoie_couleur4==0) { 
 //BA.debugLineNum = 273;BA.debugLine="Bonne_place4=0";
_bonne_place4 = (int) (0);
 //BA.debugLineNum = 274;BA.debugLine="pion4.Color=Colors.White";
mostCurrent._pion4.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 }else {
 //BA.debugLineNum = 276;BA.debugLine="Bonne_place4=0";
_bonne_place4 = (int) (0);
 //BA.debugLineNum = 277;BA.debugLine="pion4.Color=Colors.White";
mostCurrent._pion4.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 //BA.debugLineNum = 280;BA.debugLine="If Bonne_place1=1 And Bonne_place2=1 And Bonne_pl";
if (_bonne_place1==1 && _bonne_place2==1 && _bonne_place3==1 && _bonne_place4==1) { 
 //BA.debugLineNum = 281;BA.debugLine="Resulat.Color=Colors.Black";
mostCurrent._resulat.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 282;BA.debugLine="Resulat.TextColor=Colors.White";
mostCurrent._resulat.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 283;BA.debugLine="Resulat.Text=\"Bravo tu as gagné ! \"";
mostCurrent._resulat.setText((Object)("Bravo tu as gagné ! "));
 };
 //BA.debugLineNum = 286;BA.debugLine="End Sub";
return "";
}
public static String  _debug_click() throws Exception{
 //BA.debugLineNum = 288;BA.debugLine="Sub Debug_Click";
 //BA.debugLineNum = 289;BA.debugLine="If aleatoire1=1 Then";
if (_aleatoire1==1) { 
 //BA.debugLineNum = 290;BA.debugLine="couleur_aleatoire1.Color=Colors.Red";
mostCurrent._couleur_aleatoire1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 291;BA.debugLine="couleur_aleatoire1.Text=aleatoire1";
mostCurrent._couleur_aleatoire1.setText((Object)(_aleatoire1));
 };
 //BA.debugLineNum = 293;BA.debugLine="If aleatoire1=2 Then";
if (_aleatoire1==2) { 
 //BA.debugLineNum = 294;BA.debugLine="couleur_aleatoire1.Color=Colors.Blue";
mostCurrent._couleur_aleatoire1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 295;BA.debugLine="couleur_aleatoire1.Text=aleatoire1";
mostCurrent._couleur_aleatoire1.setText((Object)(_aleatoire1));
 };
 //BA.debugLineNum = 297;BA.debugLine="If aleatoire1=3 Then";
if (_aleatoire1==3) { 
 //BA.debugLineNum = 298;BA.debugLine="couleur_aleatoire1.Color=Colors.Yellow";
mostCurrent._couleur_aleatoire1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 299;BA.debugLine="couleur_aleatoire1.Text=aleatoire1";
mostCurrent._couleur_aleatoire1.setText((Object)(_aleatoire1));
 };
 //BA.debugLineNum = 301;BA.debugLine="If aleatoire1=4 Then";
if (_aleatoire1==4) { 
 //BA.debugLineNum = 302;BA.debugLine="couleur_aleatoire1.Color=Colors.Green";
mostCurrent._couleur_aleatoire1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
 //BA.debugLineNum = 303;BA.debugLine="couleur_aleatoire1.Text=aleatoire1";
mostCurrent._couleur_aleatoire1.setText((Object)(_aleatoire1));
 };
 //BA.debugLineNum = 306;BA.debugLine="If aleatoire2=1 Then";
if (_aleatoire2==1) { 
 //BA.debugLineNum = 307;BA.debugLine="couleur_aleatoire2.Color=Colors.Red";
mostCurrent._couleur_aleatoire2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 308;BA.debugLine="couleur_aleatoire2.Text=aleatoire2";
mostCurrent._couleur_aleatoire2.setText((Object)(_aleatoire2));
 };
 //BA.debugLineNum = 310;BA.debugLine="If aleatoire2=2 Then";
if (_aleatoire2==2) { 
 //BA.debugLineNum = 311;BA.debugLine="couleur_aleatoire2.Color=Colors.Blue";
mostCurrent._couleur_aleatoire2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 312;BA.debugLine="couleur_aleatoire2.Text=aleatoire2";
mostCurrent._couleur_aleatoire2.setText((Object)(_aleatoire2));
 };
 //BA.debugLineNum = 314;BA.debugLine="If aleatoire2=3 Then";
if (_aleatoire2==3) { 
 //BA.debugLineNum = 315;BA.debugLine="couleur_aleatoire2.Color=Colors.Yellow";
mostCurrent._couleur_aleatoire2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 316;BA.debugLine="couleur_aleatoire2.Text=aleatoire2";
mostCurrent._couleur_aleatoire2.setText((Object)(_aleatoire2));
 };
 //BA.debugLineNum = 318;BA.debugLine="If aleatoire2=4 Then";
if (_aleatoire2==4) { 
 //BA.debugLineNum = 319;BA.debugLine="couleur_aleatoire2.Color=Colors.Green";
mostCurrent._couleur_aleatoire2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
 //BA.debugLineNum = 320;BA.debugLine="couleur_aleatoire2.Text=aleatoire2";
mostCurrent._couleur_aleatoire2.setText((Object)(_aleatoire2));
 };
 //BA.debugLineNum = 323;BA.debugLine="If aleatoire3=1 Then";
if (_aleatoire3==1) { 
 //BA.debugLineNum = 324;BA.debugLine="couleur_aleatoire3.Color=Colors.Red";
mostCurrent._couleur_aleatoire3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 325;BA.debugLine="couleur_aleatoire3.Text=aleatoire3";
mostCurrent._couleur_aleatoire3.setText((Object)(_aleatoire3));
 };
 //BA.debugLineNum = 327;BA.debugLine="If aleatoire3=2 Then";
if (_aleatoire3==2) { 
 //BA.debugLineNum = 328;BA.debugLine="couleur_aleatoire3.Color=Colors.Blue";
mostCurrent._couleur_aleatoire3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 329;BA.debugLine="couleur_aleatoire3.Text=aleatoire3";
mostCurrent._couleur_aleatoire3.setText((Object)(_aleatoire3));
 };
 //BA.debugLineNum = 331;BA.debugLine="If aleatoire3=3 Then";
if (_aleatoire3==3) { 
 //BA.debugLineNum = 332;BA.debugLine="couleur_aleatoire3.Color=Colors.Yellow";
mostCurrent._couleur_aleatoire3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 333;BA.debugLine="couleur_aleatoire3.Text=aleatoire3";
mostCurrent._couleur_aleatoire3.setText((Object)(_aleatoire3));
 };
 //BA.debugLineNum = 335;BA.debugLine="If aleatoire3=4 Then";
if (_aleatoire3==4) { 
 //BA.debugLineNum = 336;BA.debugLine="couleur_aleatoire3.Color=Colors.Green";
mostCurrent._couleur_aleatoire3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
 //BA.debugLineNum = 337;BA.debugLine="couleur_aleatoire3.Text=aleatoire3";
mostCurrent._couleur_aleatoire3.setText((Object)(_aleatoire3));
 };
 //BA.debugLineNum = 340;BA.debugLine="If aleatoire4=1 Then";
if (_aleatoire4==1) { 
 //BA.debugLineNum = 341;BA.debugLine="couleur_aleatoire4.Color=Colors.Red";
mostCurrent._couleur_aleatoire4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 342;BA.debugLine="couleur_aleatoire4.Text=aleatoire4";
mostCurrent._couleur_aleatoire4.setText((Object)(_aleatoire4));
 };
 //BA.debugLineNum = 344;BA.debugLine="If aleatoire4=2 Then";
if (_aleatoire4==2) { 
 //BA.debugLineNum = 345;BA.debugLine="couleur_aleatoire4.Color=Colors.Blue";
mostCurrent._couleur_aleatoire4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 346;BA.debugLine="couleur_aleatoire4.Text=aleatoire4";
mostCurrent._couleur_aleatoire4.setText((Object)(_aleatoire4));
 };
 //BA.debugLineNum = 348;BA.debugLine="If aleatoire3=3 Then";
if (_aleatoire3==3) { 
 //BA.debugLineNum = 349;BA.debugLine="couleur_aleatoire4.Color=Colors.Yellow";
mostCurrent._couleur_aleatoire4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 350;BA.debugLine="couleur_aleatoire4.Text=aleatoire4";
mostCurrent._couleur_aleatoire4.setText((Object)(_aleatoire4));
 };
 //BA.debugLineNum = 352;BA.debugLine="If aleatoire3=4 Then";
if (_aleatoire3==4) { 
 //BA.debugLineNum = 353;BA.debugLine="couleur_aleatoire4.Color=Colors.Green";
mostCurrent._couleur_aleatoire4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
 //BA.debugLineNum = 354;BA.debugLine="couleur_aleatoire4.Text=aleatoire4";
mostCurrent._couleur_aleatoire4.setText((Object)(_aleatoire4));
 };
 //BA.debugLineNum = 356;BA.debugLine="End Sub";
return "";
}
public static String  _envoyer_click() throws Exception{
 //BA.debugLineNum = 180;BA.debugLine="Sub Envoyer_Click";
 //BA.debugLineNum = 181;BA.debugLine="If envoie_couleur1=1 Then";
if (_envoie_couleur1==1) { 
 //BA.debugLineNum = 182;BA.debugLine="afficher_couleur1.Color=Colors.Red";
mostCurrent._afficher_couleur1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 };
 //BA.debugLineNum = 184;BA.debugLine="If envoie_couleur1=2 Then";
if (_envoie_couleur1==2) { 
 //BA.debugLineNum = 185;BA.debugLine="afficher_couleur1.Color=Colors.Blue";
mostCurrent._afficher_couleur1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
 };
 //BA.debugLineNum = 187;BA.debugLine="If envoie_couleur1=3 Then";
if (_envoie_couleur1==3) { 
 //BA.debugLineNum = 188;BA.debugLine="afficher_couleur1.Color=Colors.Yellow";
mostCurrent._afficher_couleur1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 };
 //BA.debugLineNum = 190;BA.debugLine="If envoie_couleur1=4 Then";
if (_envoie_couleur1==4) { 
 //BA.debugLineNum = 191;BA.debugLine="afficher_couleur1.Color=Colors.Green";
mostCurrent._afficher_couleur1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
 };
 //BA.debugLineNum = 194;BA.debugLine="If envoie_couleur2=1 Then";
if (_envoie_couleur2==1) { 
 //BA.debugLineNum = 195;BA.debugLine="afficher_couleur2.Color=Colors.Red";
mostCurrent._afficher_couleur2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 };
 //BA.debugLineNum = 197;BA.debugLine="If envoie_couleur2=2 Then";
if (_envoie_couleur2==2) { 
 //BA.debugLineNum = 198;BA.debugLine="afficher_couleur2.Color=Colors.Blue";
mostCurrent._afficher_couleur2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
 };
 //BA.debugLineNum = 200;BA.debugLine="If envoie_couleur2=3 Then";
if (_envoie_couleur2==3) { 
 //BA.debugLineNum = 201;BA.debugLine="afficher_couleur2.Color=Colors.Yellow";
mostCurrent._afficher_couleur2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 };
 //BA.debugLineNum = 203;BA.debugLine="If envoie_couleur2=4 Then";
if (_envoie_couleur2==4) { 
 //BA.debugLineNum = 204;BA.debugLine="afficher_couleur2.Color=Colors.Green";
mostCurrent._afficher_couleur2.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
 };
 //BA.debugLineNum = 207;BA.debugLine="If envoie_couleur3=1 Then";
if (_envoie_couleur3==1) { 
 //BA.debugLineNum = 208;BA.debugLine="afficher_couleur3.Color=Colors.Red";
mostCurrent._afficher_couleur3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 };
 //BA.debugLineNum = 210;BA.debugLine="If envoie_couleur3=2 Then";
if (_envoie_couleur3==2) { 
 //BA.debugLineNum = 211;BA.debugLine="afficher_couleur3.Color=Colors.Blue";
mostCurrent._afficher_couleur3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
 };
 //BA.debugLineNum = 213;BA.debugLine="If envoie_couleur3=3 Then";
if (_envoie_couleur3==3) { 
 //BA.debugLineNum = 214;BA.debugLine="afficher_couleur3.Color=Colors.Yellow";
mostCurrent._afficher_couleur3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 };
 //BA.debugLineNum = 216;BA.debugLine="If envoie_couleur3=4 Then";
if (_envoie_couleur3==4) { 
 //BA.debugLineNum = 217;BA.debugLine="afficher_couleur3.Color=Colors.Green";
mostCurrent._afficher_couleur3.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
 };
 //BA.debugLineNum = 220;BA.debugLine="If envoie_couleur4=1 Then";
if (_envoie_couleur4==1) { 
 //BA.debugLineNum = 221;BA.debugLine="afficher_couleur4.Color=Colors.Red";
mostCurrent._afficher_couleur4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 };
 //BA.debugLineNum = 223;BA.debugLine="If envoie_couleur4=2 Then";
if (_envoie_couleur4==2) { 
 //BA.debugLineNum = 224;BA.debugLine="afficher_couleur4.Color=Colors.Blue";
mostCurrent._afficher_couleur4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
 };
 //BA.debugLineNum = 226;BA.debugLine="If envoie_couleur4=3 Then";
if (_envoie_couleur4==3) { 
 //BA.debugLineNum = 227;BA.debugLine="afficher_couleur4.Color=Colors.Yellow";
mostCurrent._afficher_couleur4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 };
 //BA.debugLineNum = 229;BA.debugLine="If envoie_couleur4=4 Then";
if (_envoie_couleur4==4) { 
 //BA.debugLineNum = 230;BA.debugLine="afficher_couleur4.Color=Colors.Green";
mostCurrent._afficher_couleur4.setColor(anywheresoftware.b4a.keywords.Common.Colors.Green);
 };
 //BA.debugLineNum = 232;BA.debugLine="comparaison";
_comparaison();
 //BA.debugLineNum = 233;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 20;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 21;BA.debugLine="Dim aleatoire1 As Int";
_aleatoire1 = 0;
 //BA.debugLineNum = 22;BA.debugLine="Dim aleatoire2 As Int";
_aleatoire2 = 0;
 //BA.debugLineNum = 23;BA.debugLine="Dim aleatoire3 As Int";
_aleatoire3 = 0;
 //BA.debugLineNum = 24;BA.debugLine="Dim aleatoire4 As Int";
_aleatoire4 = 0;
 //BA.debugLineNum = 25;BA.debugLine="Dim compte_couleur1 As Int";
_compte_couleur1 = 0;
 //BA.debugLineNum = 26;BA.debugLine="Dim compte_couleur2 As Int";
_compte_couleur2 = 0;
 //BA.debugLineNum = 27;BA.debugLine="Dim compte_couleur3 As Int";
_compte_couleur3 = 0;
 //BA.debugLineNum = 28;BA.debugLine="Dim compte_couleur4 As Int";
_compte_couleur4 = 0;
 //BA.debugLineNum = 29;BA.debugLine="Dim envoie_couleur1 As Int";
_envoie_couleur1 = 0;
 //BA.debugLineNum = 30;BA.debugLine="Dim envoie_couleur2 As Int";
_envoie_couleur2 = 0;
 //BA.debugLineNum = 31;BA.debugLine="Dim envoie_couleur3 As Int";
_envoie_couleur3 = 0;
 //BA.debugLineNum = 32;BA.debugLine="Dim envoie_couleur4 As Int";
_envoie_couleur4 = 0;
 //BA.debugLineNum = 33;BA.debugLine="Dim Bonne_place1 As Int";
_bonne_place1 = 0;
 //BA.debugLineNum = 34;BA.debugLine="Dim Bonne_place2 As Int";
_bonne_place2 = 0;
 //BA.debugLineNum = 35;BA.debugLine="Dim Bonne_place3 As Int";
_bonne_place3 = 0;
 //BA.debugLineNum = 36;BA.debugLine="Dim Bonne_place4 As Int";
_bonne_place4 = 0;
 //BA.debugLineNum = 37;BA.debugLine="Private couleur_aleatoire1 As Label";
mostCurrent._couleur_aleatoire1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private couleur_aleatoire2 As Label";
mostCurrent._couleur_aleatoire2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private couleur_aleatoire3 As Label";
mostCurrent._couleur_aleatoire3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private couleur_aleatoire4 As Label";
mostCurrent._couleur_aleatoire4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private choix_couleur1 As Button";
mostCurrent._choix_couleur1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private choix_couleur2 As Button";
mostCurrent._choix_couleur2 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private choix_couleur3 As Button";
mostCurrent._choix_couleur3 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private choix_couleur4 As Button";
mostCurrent._choix_couleur4 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private couleur1 As Label";
mostCurrent._couleur1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private couleur2 As Label";
mostCurrent._couleur2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private couleur3 As Label";
mostCurrent._couleur3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Private couleur4 As Label";
mostCurrent._couleur4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private afficher_couleur1 As Label";
mostCurrent._afficher_couleur1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private afficher_couleur2 As Label";
mostCurrent._afficher_couleur2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private afficher_couleur3 As Label";
mostCurrent._afficher_couleur3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 52;BA.debugLine="Private afficher_couleur4 As Label";
mostCurrent._afficher_couleur4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Private Fond As Label";
mostCurrent._fond = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 54;BA.debugLine="Private Notice_pion As Label";
mostCurrent._notice_pion = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 55;BA.debugLine="Private pion1 As Label";
mostCurrent._pion1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 56;BA.debugLine="Private pion2 As Label";
mostCurrent._pion2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 57;BA.debugLine="Private pion3 As Label";
mostCurrent._pion3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 58;BA.debugLine="Private pion4 As Label";
mostCurrent._pion4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 59;BA.debugLine="Private Resulat As Label";
mostCurrent._resulat = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 60;BA.debugLine="Private Debug As Button";
mostCurrent._debug = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 61;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
starter._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return "";
}
}
