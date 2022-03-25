package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,63);
if (RapidSub.canDelegate("activity_create")) return main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 63;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 66;BA.debugLine="Activity.LoadLayout(\"Commencer\")";
Debug.ShouldStop(2);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Commencer")),main.mostCurrent.activityBA);
 BA.debugLineNum = 67;BA.debugLine="compte_couleur1=0";
Debug.ShouldStop(4);
main._compte_couleur1 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 68;BA.debugLine="compte_couleur2=0";
Debug.ShouldStop(8);
main._compte_couleur2 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 69;BA.debugLine="compte_couleur3=0";
Debug.ShouldStop(16);
main._compte_couleur3 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 70;BA.debugLine="compte_couleur4=0";
Debug.ShouldStop(32);
main._compte_couleur4 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 71;BA.debugLine="envoie_couleur1=0";
Debug.ShouldStop(64);
main._envoie_couleur1 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 72;BA.debugLine="envoie_couleur2=0";
Debug.ShouldStop(128);
main._envoie_couleur2 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 73;BA.debugLine="envoie_couleur3=0";
Debug.ShouldStop(256);
main._envoie_couleur3 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 74;BA.debugLine="envoie_couleur4=0";
Debug.ShouldStop(512);
main._envoie_couleur4 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 75;BA.debugLine="Fond.Color=Colors.White";
Debug.ShouldStop(1024);
main.mostCurrent._fond.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 76;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,82);
if (RapidSub.canDelegate("activity_pause")) return main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 82;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(131072);
 BA.debugLineNum = 84;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,78);
if (RapidSub.canDelegate("activity_resume")) return main.remoteMe.runUserSub(false, "main","activity_resume");
 BA.debugLineNum = 78;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(8192);
 BA.debugLineNum = 80;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _choix_couleur1_click() throws Exception{
try {
		Debug.PushSubsStack("choix_couleur1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,159);
if (RapidSub.canDelegate("choix_couleur1_click")) return main.remoteMe.runUserSub(false, "main","choix_couleur1_click");
 BA.debugLineNum = 159;BA.debugLine="Sub choix_couleur1_Click";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 160;BA.debugLine="compte_couleur1=compte_couleur1+1";
Debug.ShouldStop(-2147483648);
main._compte_couleur1 = RemoteObject.solve(new RemoteObject[] {main._compte_couleur1,RemoteObject.createImmutable(1)}, "+",1, 1);
 BA.debugLineNum = 161;BA.debugLine="If compte_couleur1=1 Then";
Debug.ShouldStop(1);
if (RemoteObject.solveBoolean("=",main._compte_couleur1,BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 162;BA.debugLine="couleur1.Color=Colors.Red";
Debug.ShouldStop(2);
main.mostCurrent._couleur1.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Red"));
 BA.debugLineNum = 163;BA.debugLine="envoie_couleur1=1";
Debug.ShouldStop(4);
main._envoie_couleur1 = BA.numberCast(int.class, 1);
 };
 BA.debugLineNum = 165;BA.debugLine="If compte_couleur1=2 Then";
Debug.ShouldStop(16);
if (RemoteObject.solveBoolean("=",main._compte_couleur1,BA.numberCast(double.class, 2))) { 
 BA.debugLineNum = 166;BA.debugLine="couleur1.Color=Colors.Blue";
Debug.ShouldStop(32);
main.mostCurrent._couleur1.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Blue"));
 BA.debugLineNum = 167;BA.debugLine="envoie_couleur1=2";
Debug.ShouldStop(64);
main._envoie_couleur1 = BA.numberCast(int.class, 2);
 };
 BA.debugLineNum = 169;BA.debugLine="If compte_couleur1=3 Then";
Debug.ShouldStop(256);
if (RemoteObject.solveBoolean("=",main._compte_couleur1,BA.numberCast(double.class, 3))) { 
 BA.debugLineNum = 170;BA.debugLine="couleur1.Color=Colors.Yellow";
Debug.ShouldStop(512);
main.mostCurrent._couleur1.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Yellow"));
 BA.debugLineNum = 171;BA.debugLine="envoie_couleur1=3";
Debug.ShouldStop(1024);
main._envoie_couleur1 = BA.numberCast(int.class, 3);
 };
 BA.debugLineNum = 173;BA.debugLine="If compte_couleur1=4 Then";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean("=",main._compte_couleur1,BA.numberCast(double.class, 4))) { 
 BA.debugLineNum = 174;BA.debugLine="couleur1.Color=Colors.Green";
Debug.ShouldStop(8192);
main.mostCurrent._couleur1.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Green"));
 BA.debugLineNum = 175;BA.debugLine="compte_couleur1=0";
Debug.ShouldStop(16384);
main._compte_couleur1 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 176;BA.debugLine="envoie_couleur1=4";
Debug.ShouldStop(32768);
main._envoie_couleur1 = BA.numberCast(int.class, 4);
 };
 BA.debugLineNum = 178;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _choix_couleur2_click() throws Exception{
try {
		Debug.PushSubsStack("choix_couleur2_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,138);
if (RapidSub.canDelegate("choix_couleur2_click")) return main.remoteMe.runUserSub(false, "main","choix_couleur2_click");
 BA.debugLineNum = 138;BA.debugLine="Sub choix_couleur2_Click";
Debug.ShouldStop(512);
 BA.debugLineNum = 139;BA.debugLine="compte_couleur2=compte_couleur2+1";
Debug.ShouldStop(1024);
main._compte_couleur2 = RemoteObject.solve(new RemoteObject[] {main._compte_couleur2,RemoteObject.createImmutable(1)}, "+",1, 1);
 BA.debugLineNum = 140;BA.debugLine="If compte_couleur2=1 Then";
Debug.ShouldStop(2048);
if (RemoteObject.solveBoolean("=",main._compte_couleur2,BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 141;BA.debugLine="couleur2.Color=Colors.Red";
Debug.ShouldStop(4096);
main.mostCurrent._couleur2.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Red"));
 BA.debugLineNum = 142;BA.debugLine="envoie_couleur2=1";
Debug.ShouldStop(8192);
main._envoie_couleur2 = BA.numberCast(int.class, 1);
 };
 BA.debugLineNum = 144;BA.debugLine="If compte_couleur2=2 Then";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean("=",main._compte_couleur2,BA.numberCast(double.class, 2))) { 
 BA.debugLineNum = 145;BA.debugLine="couleur2.Color=Colors.Blue";
Debug.ShouldStop(65536);
main.mostCurrent._couleur2.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Blue"));
 BA.debugLineNum = 146;BA.debugLine="envoie_couleur2=2";
Debug.ShouldStop(131072);
main._envoie_couleur2 = BA.numberCast(int.class, 2);
 };
 BA.debugLineNum = 148;BA.debugLine="If compte_couleur2=3 Then";
Debug.ShouldStop(524288);
if (RemoteObject.solveBoolean("=",main._compte_couleur2,BA.numberCast(double.class, 3))) { 
 BA.debugLineNum = 149;BA.debugLine="couleur2.Color=Colors.Yellow";
Debug.ShouldStop(1048576);
main.mostCurrent._couleur2.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Yellow"));
 BA.debugLineNum = 150;BA.debugLine="envoie_couleur2=3";
Debug.ShouldStop(2097152);
main._envoie_couleur2 = BA.numberCast(int.class, 3);
 };
 BA.debugLineNum = 152;BA.debugLine="If compte_couleur2=4 Then";
Debug.ShouldStop(8388608);
if (RemoteObject.solveBoolean("=",main._compte_couleur2,BA.numberCast(double.class, 4))) { 
 BA.debugLineNum = 153;BA.debugLine="couleur2.Color=Colors.Green";
Debug.ShouldStop(16777216);
main.mostCurrent._couleur2.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Green"));
 BA.debugLineNum = 154;BA.debugLine="compte_couleur2=0";
Debug.ShouldStop(33554432);
main._compte_couleur2 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 155;BA.debugLine="envoie_couleur2=4";
Debug.ShouldStop(67108864);
main._envoie_couleur2 = BA.numberCast(int.class, 4);
 };
 BA.debugLineNum = 157;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _choix_couleur3_click() throws Exception{
try {
		Debug.PushSubsStack("choix_couleur3_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,117);
if (RapidSub.canDelegate("choix_couleur3_click")) return main.remoteMe.runUserSub(false, "main","choix_couleur3_click");
 BA.debugLineNum = 117;BA.debugLine="Sub choix_couleur3_Click";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 118;BA.debugLine="compte_couleur3=compte_couleur3+1";
Debug.ShouldStop(2097152);
main._compte_couleur3 = RemoteObject.solve(new RemoteObject[] {main._compte_couleur3,RemoteObject.createImmutable(1)}, "+",1, 1);
 BA.debugLineNum = 119;BA.debugLine="If compte_couleur3=1 Then";
Debug.ShouldStop(4194304);
if (RemoteObject.solveBoolean("=",main._compte_couleur3,BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 120;BA.debugLine="couleur3.Color=Colors.Red";
Debug.ShouldStop(8388608);
main.mostCurrent._couleur3.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Red"));
 BA.debugLineNum = 121;BA.debugLine="envoie_couleur3=1";
Debug.ShouldStop(16777216);
main._envoie_couleur3 = BA.numberCast(int.class, 1);
 };
 BA.debugLineNum = 123;BA.debugLine="If compte_couleur3=2 Then";
Debug.ShouldStop(67108864);
if (RemoteObject.solveBoolean("=",main._compte_couleur3,BA.numberCast(double.class, 2))) { 
 BA.debugLineNum = 124;BA.debugLine="couleur3.Color=Colors.Blue";
Debug.ShouldStop(134217728);
main.mostCurrent._couleur3.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Blue"));
 BA.debugLineNum = 125;BA.debugLine="envoie_couleur3=2";
Debug.ShouldStop(268435456);
main._envoie_couleur3 = BA.numberCast(int.class, 2);
 };
 BA.debugLineNum = 127;BA.debugLine="If compte_couleur3=3 Then";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean("=",main._compte_couleur3,BA.numberCast(double.class, 3))) { 
 BA.debugLineNum = 128;BA.debugLine="couleur3.Color=Colors.Yellow";
Debug.ShouldStop(-2147483648);
main.mostCurrent._couleur3.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Yellow"));
 BA.debugLineNum = 129;BA.debugLine="envoie_couleur3=3";
Debug.ShouldStop(1);
main._envoie_couleur3 = BA.numberCast(int.class, 3);
 };
 BA.debugLineNum = 131;BA.debugLine="If compte_couleur3=4 Then";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean("=",main._compte_couleur3,BA.numberCast(double.class, 4))) { 
 BA.debugLineNum = 132;BA.debugLine="couleur3.Color=Colors.Green";
Debug.ShouldStop(8);
main.mostCurrent._couleur3.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Green"));
 BA.debugLineNum = 133;BA.debugLine="compte_couleur3=0";
Debug.ShouldStop(16);
main._compte_couleur3 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 134;BA.debugLine="envoie_couleur3=4";
Debug.ShouldStop(32);
main._envoie_couleur3 = BA.numberCast(int.class, 4);
 };
 BA.debugLineNum = 136;BA.debugLine="End Sub";
Debug.ShouldStop(128);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _choix_couleur4_click() throws Exception{
try {
		Debug.PushSubsStack("choix_couleur4_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,96);
if (RapidSub.canDelegate("choix_couleur4_click")) return main.remoteMe.runUserSub(false, "main","choix_couleur4_click");
 BA.debugLineNum = 96;BA.debugLine="Sub choix_couleur4_Click";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 97;BA.debugLine="compte_couleur4=compte_couleur4+1";
Debug.ShouldStop(1);
main._compte_couleur4 = RemoteObject.solve(new RemoteObject[] {main._compte_couleur4,RemoteObject.createImmutable(1)}, "+",1, 1);
 BA.debugLineNum = 98;BA.debugLine="If compte_couleur4=1 Then";
Debug.ShouldStop(2);
if (RemoteObject.solveBoolean("=",main._compte_couleur4,BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 99;BA.debugLine="couleur4.Color=Colors.Red";
Debug.ShouldStop(4);
main.mostCurrent._couleur4.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Red"));
 BA.debugLineNum = 100;BA.debugLine="envoie_couleur4=1";
Debug.ShouldStop(8);
main._envoie_couleur4 = BA.numberCast(int.class, 1);
 };
 BA.debugLineNum = 102;BA.debugLine="If compte_couleur4=2 Then";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean("=",main._compte_couleur4,BA.numberCast(double.class, 2))) { 
 BA.debugLineNum = 103;BA.debugLine="couleur4.Color=Colors.Blue";
Debug.ShouldStop(64);
main.mostCurrent._couleur4.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Blue"));
 BA.debugLineNum = 104;BA.debugLine="envoie_couleur4=2";
Debug.ShouldStop(128);
main._envoie_couleur4 = BA.numberCast(int.class, 2);
 };
 BA.debugLineNum = 106;BA.debugLine="If compte_couleur4=3 Then";
Debug.ShouldStop(512);
if (RemoteObject.solveBoolean("=",main._compte_couleur4,BA.numberCast(double.class, 3))) { 
 BA.debugLineNum = 107;BA.debugLine="couleur4.Color=Colors.Yellow";
Debug.ShouldStop(1024);
main.mostCurrent._couleur4.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Yellow"));
 BA.debugLineNum = 108;BA.debugLine="envoie_couleur4=3";
Debug.ShouldStop(2048);
main._envoie_couleur4 = BA.numberCast(int.class, 3);
 };
 BA.debugLineNum = 110;BA.debugLine="If compte_couleur4=4 Then";
Debug.ShouldStop(8192);
if (RemoteObject.solveBoolean("=",main._compte_couleur4,BA.numberCast(double.class, 4))) { 
 BA.debugLineNum = 111;BA.debugLine="couleur4.Color=Colors.Green";
Debug.ShouldStop(16384);
main.mostCurrent._couleur4.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Green"));
 BA.debugLineNum = 112;BA.debugLine="compte_couleur4=0";
Debug.ShouldStop(32768);
main._compte_couleur4 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 113;BA.debugLine="envoie_couleur4=4";
Debug.ShouldStop(65536);
main._envoie_couleur4 = BA.numberCast(int.class, 4);
 };
 BA.debugLineNum = 115;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _commencer_click() throws Exception{
try {
		Debug.PushSubsStack("Commencer_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,86);
if (RapidSub.canDelegate("commencer_click")) return main.remoteMe.runUserSub(false, "main","commencer_click");
 BA.debugLineNum = 86;BA.debugLine="Sub Commencer_Click";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 87;BA.debugLine="aleatoire1=Rnd(1,4)";
Debug.ShouldStop(4194304);
main._aleatoire1 = main.mostCurrent.__c.runMethod(true,"Rnd",(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 4)));
 BA.debugLineNum = 88;BA.debugLine="aleatoire2=Rnd(1,4)";
Debug.ShouldStop(8388608);
main._aleatoire2 = main.mostCurrent.__c.runMethod(true,"Rnd",(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 4)));
 BA.debugLineNum = 89;BA.debugLine="aleatoire3=Rnd(1,4)";
Debug.ShouldStop(16777216);
main._aleatoire3 = main.mostCurrent.__c.runMethod(true,"Rnd",(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 4)));
 BA.debugLineNum = 90;BA.debugLine="aleatoire4=Rnd(1,4)";
Debug.ShouldStop(33554432);
main._aleatoire4 = main.mostCurrent.__c.runMethod(true,"Rnd",(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 4)));
 BA.debugLineNum = 91;BA.debugLine="Resulat.Color=Colors.Black";
Debug.ShouldStop(67108864);
main.mostCurrent._resulat.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 92;BA.debugLine="Resulat.TextColor=Colors.White";
Debug.ShouldStop(134217728);
main.mostCurrent._resulat.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 93;BA.debugLine="Resulat.Text=\"Choisir les couleurs : Rouge, Bleu,";
Debug.ShouldStop(268435456);
main.mostCurrent._resulat.runMethod(true,"setText",RemoteObject.createImmutable(("Choisir les couleurs : Rouge, Bleu, Verte et Jaune")));
 BA.debugLineNum = 94;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _comparaison() throws Exception{
try {
		Debug.PushSubsStack("comparaison (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,235);
if (RapidSub.canDelegate("comparaison")) return main.remoteMe.runUserSub(false, "main","comparaison");
 BA.debugLineNum = 235;BA.debugLine="Sub comparaison";
Debug.ShouldStop(1024);
 BA.debugLineNum = 236;BA.debugLine="If envoie_couleur1=aleatoire1 Then";
Debug.ShouldStop(2048);
if (RemoteObject.solveBoolean("=",main._envoie_couleur1,BA.numberCast(double.class, main._aleatoire1))) { 
 BA.debugLineNum = 237;BA.debugLine="Bonne_place1=1";
Debug.ShouldStop(4096);
main._bonne_place1 = BA.numberCast(int.class, 1);
 BA.debugLineNum = 238;BA.debugLine="pion1.Color=Colors.Black";
Debug.ShouldStop(8192);
main.mostCurrent._pion1.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 }else 
{ BA.debugLineNum = 239;BA.debugLine="Else If envoie_couleur1=0 Then";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean("=",main._envoie_couleur1,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 240;BA.debugLine="Bonne_place1=0";
Debug.ShouldStop(32768);
main._bonne_place1 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 241;BA.debugLine="pion1.Color=Colors.White";
Debug.ShouldStop(65536);
main.mostCurrent._pion1.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 243;BA.debugLine="Bonne_place1=0";
Debug.ShouldStop(262144);
main._bonne_place1 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 244;BA.debugLine="pion1.Color=Colors.White";
Debug.ShouldStop(524288);
main.mostCurrent._pion1.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }};
 BA.debugLineNum = 247;BA.debugLine="If envoie_couleur2=aleatoire2 Then";
Debug.ShouldStop(4194304);
if (RemoteObject.solveBoolean("=",main._envoie_couleur2,BA.numberCast(double.class, main._aleatoire2))) { 
 BA.debugLineNum = 248;BA.debugLine="Bonne_place2=1";
Debug.ShouldStop(8388608);
main._bonne_place2 = BA.numberCast(int.class, 1);
 BA.debugLineNum = 249;BA.debugLine="pion2.Color=Colors.Black";
Debug.ShouldStop(16777216);
main.mostCurrent._pion2.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 }else 
{ BA.debugLineNum = 250;BA.debugLine="Else If envoie_couleur2=0 Then";
Debug.ShouldStop(33554432);
if (RemoteObject.solveBoolean("=",main._envoie_couleur2,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 251;BA.debugLine="Bonne_place2=0";
Debug.ShouldStop(67108864);
main._bonne_place2 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 252;BA.debugLine="pion2.Color=Colors.White";
Debug.ShouldStop(134217728);
main.mostCurrent._pion2.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 254;BA.debugLine="Bonne_place2=0";
Debug.ShouldStop(536870912);
main._bonne_place2 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 255;BA.debugLine="pion2.Color=Colors.White";
Debug.ShouldStop(1073741824);
main.mostCurrent._pion2.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }};
 BA.debugLineNum = 258;BA.debugLine="If envoie_couleur3=aleatoire3 Then";
Debug.ShouldStop(2);
if (RemoteObject.solveBoolean("=",main._envoie_couleur3,BA.numberCast(double.class, main._aleatoire3))) { 
 BA.debugLineNum = 259;BA.debugLine="Bonne_place3=1";
Debug.ShouldStop(4);
main._bonne_place3 = BA.numberCast(int.class, 1);
 BA.debugLineNum = 260;BA.debugLine="pion3.Color=Colors.Black";
Debug.ShouldStop(8);
main.mostCurrent._pion3.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 }else 
{ BA.debugLineNum = 261;BA.debugLine="Else If envoie_couleur3=0 Then";
Debug.ShouldStop(16);
if (RemoteObject.solveBoolean("=",main._envoie_couleur3,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 262;BA.debugLine="Bonne_place3=0";
Debug.ShouldStop(32);
main._bonne_place3 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 263;BA.debugLine="pion3.Color=Colors.White";
Debug.ShouldStop(64);
main.mostCurrent._pion3.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 265;BA.debugLine="Bonne_place3=0";
Debug.ShouldStop(256);
main._bonne_place3 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 266;BA.debugLine="pion3.Color=Colors.White";
Debug.ShouldStop(512);
main.mostCurrent._pion3.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }};
 BA.debugLineNum = 269;BA.debugLine="If envoie_couleur4=aleatoire4 Then";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean("=",main._envoie_couleur4,BA.numberCast(double.class, main._aleatoire4))) { 
 BA.debugLineNum = 270;BA.debugLine="Bonne_place4=1";
Debug.ShouldStop(8192);
main._bonne_place4 = BA.numberCast(int.class, 1);
 BA.debugLineNum = 271;BA.debugLine="pion4.Color=Colors.Black";
Debug.ShouldStop(16384);
main.mostCurrent._pion4.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 }else 
{ BA.debugLineNum = 272;BA.debugLine="Else If envoie_couleur4=0 Then";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean("=",main._envoie_couleur4,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 273;BA.debugLine="Bonne_place4=0";
Debug.ShouldStop(65536);
main._bonne_place4 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 274;BA.debugLine="pion4.Color=Colors.White";
Debug.ShouldStop(131072);
main.mostCurrent._pion4.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }else {
 BA.debugLineNum = 276;BA.debugLine="Bonne_place4=0";
Debug.ShouldStop(524288);
main._bonne_place4 = BA.numberCast(int.class, 0);
 BA.debugLineNum = 277;BA.debugLine="pion4.Color=Colors.White";
Debug.ShouldStop(1048576);
main.mostCurrent._pion4.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 }};
 BA.debugLineNum = 280;BA.debugLine="If Bonne_place1=1 And Bonne_place2=1 And Bonne_pl";
Debug.ShouldStop(8388608);
if (RemoteObject.solveBoolean("=",main._bonne_place1,BA.numberCast(double.class, 1)) && RemoteObject.solveBoolean("=",main._bonne_place2,BA.numberCast(double.class, 1)) && RemoteObject.solveBoolean("=",main._bonne_place3,BA.numberCast(double.class, 1)) && RemoteObject.solveBoolean("=",main._bonne_place4,BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 281;BA.debugLine="Resulat.Color=Colors.Black";
Debug.ShouldStop(16777216);
main.mostCurrent._resulat.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Black"));
 BA.debugLineNum = 282;BA.debugLine="Resulat.TextColor=Colors.White";
Debug.ShouldStop(33554432);
main.mostCurrent._resulat.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 283;BA.debugLine="Resulat.Text=\"Bravo tu as gagné ! \"";
Debug.ShouldStop(67108864);
main.mostCurrent._resulat.runMethod(true,"setText",RemoteObject.createImmutable(("Bravo tu as gagné ! ")));
 };
 BA.debugLineNum = 286;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _debug_click() throws Exception{
try {
		Debug.PushSubsStack("Debug_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,288);
if (RapidSub.canDelegate("debug_click")) return main.remoteMe.runUserSub(false, "main","debug_click");
 BA.debugLineNum = 288;BA.debugLine="Sub Debug_Click";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 289;BA.debugLine="If aleatoire1=1 Then";
Debug.ShouldStop(1);
if (RemoteObject.solveBoolean("=",main._aleatoire1,BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 290;BA.debugLine="couleur_aleatoire1.Color=Colors.Red";
Debug.ShouldStop(2);
main.mostCurrent._couleur_aleatoire1.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Red"));
 BA.debugLineNum = 291;BA.debugLine="couleur_aleatoire1.Text=aleatoire1";
Debug.ShouldStop(4);
main.mostCurrent._couleur_aleatoire1.runMethod(true,"setText",(main._aleatoire1));
 };
 BA.debugLineNum = 293;BA.debugLine="If aleatoire1=2 Then";
Debug.ShouldStop(16);
if (RemoteObject.solveBoolean("=",main._aleatoire1,BA.numberCast(double.class, 2))) { 
 BA.debugLineNum = 294;BA.debugLine="couleur_aleatoire1.Color=Colors.Blue";
Debug.ShouldStop(32);
main.mostCurrent._couleur_aleatoire1.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Blue"));
 BA.debugLineNum = 295;BA.debugLine="couleur_aleatoire1.Text=aleatoire1";
Debug.ShouldStop(64);
main.mostCurrent._couleur_aleatoire1.runMethod(true,"setText",(main._aleatoire1));
 };
 BA.debugLineNum = 297;BA.debugLine="If aleatoire1=3 Then";
Debug.ShouldStop(256);
if (RemoteObject.solveBoolean("=",main._aleatoire1,BA.numberCast(double.class, 3))) { 
 BA.debugLineNum = 298;BA.debugLine="couleur_aleatoire1.Color=Colors.Yellow";
Debug.ShouldStop(512);
main.mostCurrent._couleur_aleatoire1.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Yellow"));
 BA.debugLineNum = 299;BA.debugLine="couleur_aleatoire1.Text=aleatoire1";
Debug.ShouldStop(1024);
main.mostCurrent._couleur_aleatoire1.runMethod(true,"setText",(main._aleatoire1));
 };
 BA.debugLineNum = 301;BA.debugLine="If aleatoire1=4 Then";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean("=",main._aleatoire1,BA.numberCast(double.class, 4))) { 
 BA.debugLineNum = 302;BA.debugLine="couleur_aleatoire1.Color=Colors.Green";
Debug.ShouldStop(8192);
main.mostCurrent._couleur_aleatoire1.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Green"));
 BA.debugLineNum = 303;BA.debugLine="couleur_aleatoire1.Text=aleatoire1";
Debug.ShouldStop(16384);
main.mostCurrent._couleur_aleatoire1.runMethod(true,"setText",(main._aleatoire1));
 };
 BA.debugLineNum = 306;BA.debugLine="If aleatoire2=1 Then";
Debug.ShouldStop(131072);
if (RemoteObject.solveBoolean("=",main._aleatoire2,BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 307;BA.debugLine="couleur_aleatoire2.Color=Colors.Red";
Debug.ShouldStop(262144);
main.mostCurrent._couleur_aleatoire2.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Red"));
 BA.debugLineNum = 308;BA.debugLine="couleur_aleatoire2.Text=aleatoire2";
Debug.ShouldStop(524288);
main.mostCurrent._couleur_aleatoire2.runMethod(true,"setText",(main._aleatoire2));
 };
 BA.debugLineNum = 310;BA.debugLine="If aleatoire2=2 Then";
Debug.ShouldStop(2097152);
if (RemoteObject.solveBoolean("=",main._aleatoire2,BA.numberCast(double.class, 2))) { 
 BA.debugLineNum = 311;BA.debugLine="couleur_aleatoire2.Color=Colors.Blue";
Debug.ShouldStop(4194304);
main.mostCurrent._couleur_aleatoire2.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Blue"));
 BA.debugLineNum = 312;BA.debugLine="couleur_aleatoire2.Text=aleatoire2";
Debug.ShouldStop(8388608);
main.mostCurrent._couleur_aleatoire2.runMethod(true,"setText",(main._aleatoire2));
 };
 BA.debugLineNum = 314;BA.debugLine="If aleatoire2=3 Then";
Debug.ShouldStop(33554432);
if (RemoteObject.solveBoolean("=",main._aleatoire2,BA.numberCast(double.class, 3))) { 
 BA.debugLineNum = 315;BA.debugLine="couleur_aleatoire2.Color=Colors.Yellow";
Debug.ShouldStop(67108864);
main.mostCurrent._couleur_aleatoire2.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Yellow"));
 BA.debugLineNum = 316;BA.debugLine="couleur_aleatoire2.Text=aleatoire2";
Debug.ShouldStop(134217728);
main.mostCurrent._couleur_aleatoire2.runMethod(true,"setText",(main._aleatoire2));
 };
 BA.debugLineNum = 318;BA.debugLine="If aleatoire2=4 Then";
Debug.ShouldStop(536870912);
if (RemoteObject.solveBoolean("=",main._aleatoire2,BA.numberCast(double.class, 4))) { 
 BA.debugLineNum = 319;BA.debugLine="couleur_aleatoire2.Color=Colors.Green";
Debug.ShouldStop(1073741824);
main.mostCurrent._couleur_aleatoire2.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Green"));
 BA.debugLineNum = 320;BA.debugLine="couleur_aleatoire2.Text=aleatoire2";
Debug.ShouldStop(-2147483648);
main.mostCurrent._couleur_aleatoire2.runMethod(true,"setText",(main._aleatoire2));
 };
 BA.debugLineNum = 323;BA.debugLine="If aleatoire3=1 Then";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean("=",main._aleatoire3,BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 324;BA.debugLine="couleur_aleatoire3.Color=Colors.Red";
Debug.ShouldStop(8);
main.mostCurrent._couleur_aleatoire3.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Red"));
 BA.debugLineNum = 325;BA.debugLine="couleur_aleatoire3.Text=aleatoire3";
Debug.ShouldStop(16);
main.mostCurrent._couleur_aleatoire3.runMethod(true,"setText",(main._aleatoire3));
 };
 BA.debugLineNum = 327;BA.debugLine="If aleatoire3=2 Then";
Debug.ShouldStop(64);
if (RemoteObject.solveBoolean("=",main._aleatoire3,BA.numberCast(double.class, 2))) { 
 BA.debugLineNum = 328;BA.debugLine="couleur_aleatoire3.Color=Colors.Blue";
Debug.ShouldStop(128);
main.mostCurrent._couleur_aleatoire3.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Blue"));
 BA.debugLineNum = 329;BA.debugLine="couleur_aleatoire3.Text=aleatoire3";
Debug.ShouldStop(256);
main.mostCurrent._couleur_aleatoire3.runMethod(true,"setText",(main._aleatoire3));
 };
 BA.debugLineNum = 331;BA.debugLine="If aleatoire3=3 Then";
Debug.ShouldStop(1024);
if (RemoteObject.solveBoolean("=",main._aleatoire3,BA.numberCast(double.class, 3))) { 
 BA.debugLineNum = 332;BA.debugLine="couleur_aleatoire3.Color=Colors.Yellow";
Debug.ShouldStop(2048);
main.mostCurrent._couleur_aleatoire3.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Yellow"));
 BA.debugLineNum = 333;BA.debugLine="couleur_aleatoire3.Text=aleatoire3";
Debug.ShouldStop(4096);
main.mostCurrent._couleur_aleatoire3.runMethod(true,"setText",(main._aleatoire3));
 };
 BA.debugLineNum = 335;BA.debugLine="If aleatoire3=4 Then";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean("=",main._aleatoire3,BA.numberCast(double.class, 4))) { 
 BA.debugLineNum = 336;BA.debugLine="couleur_aleatoire3.Color=Colors.Green";
Debug.ShouldStop(32768);
main.mostCurrent._couleur_aleatoire3.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Green"));
 BA.debugLineNum = 337;BA.debugLine="couleur_aleatoire3.Text=aleatoire3";
Debug.ShouldStop(65536);
main.mostCurrent._couleur_aleatoire3.runMethod(true,"setText",(main._aleatoire3));
 };
 BA.debugLineNum = 340;BA.debugLine="If aleatoire4=1 Then";
Debug.ShouldStop(524288);
if (RemoteObject.solveBoolean("=",main._aleatoire4,BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 341;BA.debugLine="couleur_aleatoire4.Color=Colors.Red";
Debug.ShouldStop(1048576);
main.mostCurrent._couleur_aleatoire4.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Red"));
 BA.debugLineNum = 342;BA.debugLine="couleur_aleatoire4.Text=aleatoire4";
Debug.ShouldStop(2097152);
main.mostCurrent._couleur_aleatoire4.runMethod(true,"setText",(main._aleatoire4));
 };
 BA.debugLineNum = 344;BA.debugLine="If aleatoire4=2 Then";
Debug.ShouldStop(8388608);
if (RemoteObject.solveBoolean("=",main._aleatoire4,BA.numberCast(double.class, 2))) { 
 BA.debugLineNum = 345;BA.debugLine="couleur_aleatoire4.Color=Colors.Blue";
Debug.ShouldStop(16777216);
main.mostCurrent._couleur_aleatoire4.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Blue"));
 BA.debugLineNum = 346;BA.debugLine="couleur_aleatoire4.Text=aleatoire4";
Debug.ShouldStop(33554432);
main.mostCurrent._couleur_aleatoire4.runMethod(true,"setText",(main._aleatoire4));
 };
 BA.debugLineNum = 348;BA.debugLine="If aleatoire3=3 Then";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean("=",main._aleatoire3,BA.numberCast(double.class, 3))) { 
 BA.debugLineNum = 349;BA.debugLine="couleur_aleatoire4.Color=Colors.Yellow";
Debug.ShouldStop(268435456);
main.mostCurrent._couleur_aleatoire4.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Yellow"));
 BA.debugLineNum = 350;BA.debugLine="couleur_aleatoire4.Text=aleatoire4";
Debug.ShouldStop(536870912);
main.mostCurrent._couleur_aleatoire4.runMethod(true,"setText",(main._aleatoire4));
 };
 BA.debugLineNum = 352;BA.debugLine="If aleatoire3=4 Then";
Debug.ShouldStop(-2147483648);
if (RemoteObject.solveBoolean("=",main._aleatoire3,BA.numberCast(double.class, 4))) { 
 BA.debugLineNum = 353;BA.debugLine="couleur_aleatoire4.Color=Colors.Green";
Debug.ShouldStop(1);
main.mostCurrent._couleur_aleatoire4.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Green"));
 BA.debugLineNum = 354;BA.debugLine="couleur_aleatoire4.Text=aleatoire4";
Debug.ShouldStop(2);
main.mostCurrent._couleur_aleatoire4.runMethod(true,"setText",(main._aleatoire4));
 };
 BA.debugLineNum = 356;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _envoyer_click() throws Exception{
try {
		Debug.PushSubsStack("Envoyer_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,180);
if (RapidSub.canDelegate("envoyer_click")) return main.remoteMe.runUserSub(false, "main","envoyer_click");
 BA.debugLineNum = 180;BA.debugLine="Sub Envoyer_Click";
Debug.ShouldStop(524288);
 BA.debugLineNum = 181;BA.debugLine="If envoie_couleur1=1 Then";
Debug.ShouldStop(1048576);
if (RemoteObject.solveBoolean("=",main._envoie_couleur1,BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 182;BA.debugLine="afficher_couleur1.Color=Colors.Red";
Debug.ShouldStop(2097152);
main.mostCurrent._afficher_couleur1.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Red"));
 };
 BA.debugLineNum = 184;BA.debugLine="If envoie_couleur1=2 Then";
Debug.ShouldStop(8388608);
if (RemoteObject.solveBoolean("=",main._envoie_couleur1,BA.numberCast(double.class, 2))) { 
 BA.debugLineNum = 185;BA.debugLine="afficher_couleur1.Color=Colors.Blue";
Debug.ShouldStop(16777216);
main.mostCurrent._afficher_couleur1.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Blue"));
 };
 BA.debugLineNum = 187;BA.debugLine="If envoie_couleur1=3 Then";
Debug.ShouldStop(67108864);
if (RemoteObject.solveBoolean("=",main._envoie_couleur1,BA.numberCast(double.class, 3))) { 
 BA.debugLineNum = 188;BA.debugLine="afficher_couleur1.Color=Colors.Yellow";
Debug.ShouldStop(134217728);
main.mostCurrent._afficher_couleur1.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Yellow"));
 };
 BA.debugLineNum = 190;BA.debugLine="If envoie_couleur1=4 Then";
Debug.ShouldStop(536870912);
if (RemoteObject.solveBoolean("=",main._envoie_couleur1,BA.numberCast(double.class, 4))) { 
 BA.debugLineNum = 191;BA.debugLine="afficher_couleur1.Color=Colors.Green";
Debug.ShouldStop(1073741824);
main.mostCurrent._afficher_couleur1.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Green"));
 };
 BA.debugLineNum = 194;BA.debugLine="If envoie_couleur2=1 Then";
Debug.ShouldStop(2);
if (RemoteObject.solveBoolean("=",main._envoie_couleur2,BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 195;BA.debugLine="afficher_couleur2.Color=Colors.Red";
Debug.ShouldStop(4);
main.mostCurrent._afficher_couleur2.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Red"));
 };
 BA.debugLineNum = 197;BA.debugLine="If envoie_couleur2=2 Then";
Debug.ShouldStop(16);
if (RemoteObject.solveBoolean("=",main._envoie_couleur2,BA.numberCast(double.class, 2))) { 
 BA.debugLineNum = 198;BA.debugLine="afficher_couleur2.Color=Colors.Blue";
Debug.ShouldStop(32);
main.mostCurrent._afficher_couleur2.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Blue"));
 };
 BA.debugLineNum = 200;BA.debugLine="If envoie_couleur2=3 Then";
Debug.ShouldStop(128);
if (RemoteObject.solveBoolean("=",main._envoie_couleur2,BA.numberCast(double.class, 3))) { 
 BA.debugLineNum = 201;BA.debugLine="afficher_couleur2.Color=Colors.Yellow";
Debug.ShouldStop(256);
main.mostCurrent._afficher_couleur2.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Yellow"));
 };
 BA.debugLineNum = 203;BA.debugLine="If envoie_couleur2=4 Then";
Debug.ShouldStop(1024);
if (RemoteObject.solveBoolean("=",main._envoie_couleur2,BA.numberCast(double.class, 4))) { 
 BA.debugLineNum = 204;BA.debugLine="afficher_couleur2.Color=Colors.Green";
Debug.ShouldStop(2048);
main.mostCurrent._afficher_couleur2.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Green"));
 };
 BA.debugLineNum = 207;BA.debugLine="If envoie_couleur3=1 Then";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean("=",main._envoie_couleur3,BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 208;BA.debugLine="afficher_couleur3.Color=Colors.Red";
Debug.ShouldStop(32768);
main.mostCurrent._afficher_couleur3.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Red"));
 };
 BA.debugLineNum = 210;BA.debugLine="If envoie_couleur3=2 Then";
Debug.ShouldStop(131072);
if (RemoteObject.solveBoolean("=",main._envoie_couleur3,BA.numberCast(double.class, 2))) { 
 BA.debugLineNum = 211;BA.debugLine="afficher_couleur3.Color=Colors.Blue";
Debug.ShouldStop(262144);
main.mostCurrent._afficher_couleur3.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Blue"));
 };
 BA.debugLineNum = 213;BA.debugLine="If envoie_couleur3=3 Then";
Debug.ShouldStop(1048576);
if (RemoteObject.solveBoolean("=",main._envoie_couleur3,BA.numberCast(double.class, 3))) { 
 BA.debugLineNum = 214;BA.debugLine="afficher_couleur3.Color=Colors.Yellow";
Debug.ShouldStop(2097152);
main.mostCurrent._afficher_couleur3.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Yellow"));
 };
 BA.debugLineNum = 216;BA.debugLine="If envoie_couleur3=4 Then";
Debug.ShouldStop(8388608);
if (RemoteObject.solveBoolean("=",main._envoie_couleur3,BA.numberCast(double.class, 4))) { 
 BA.debugLineNum = 217;BA.debugLine="afficher_couleur3.Color=Colors.Green";
Debug.ShouldStop(16777216);
main.mostCurrent._afficher_couleur3.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Green"));
 };
 BA.debugLineNum = 220;BA.debugLine="If envoie_couleur4=1 Then";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean("=",main._envoie_couleur4,BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 221;BA.debugLine="afficher_couleur4.Color=Colors.Red";
Debug.ShouldStop(268435456);
main.mostCurrent._afficher_couleur4.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Red"));
 };
 BA.debugLineNum = 223;BA.debugLine="If envoie_couleur4=2 Then";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean("=",main._envoie_couleur4,BA.numberCast(double.class, 2))) { 
 BA.debugLineNum = 224;BA.debugLine="afficher_couleur4.Color=Colors.Blue";
Debug.ShouldStop(-2147483648);
main.mostCurrent._afficher_couleur4.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Blue"));
 };
 BA.debugLineNum = 226;BA.debugLine="If envoie_couleur4=3 Then";
Debug.ShouldStop(2);
if (RemoteObject.solveBoolean("=",main._envoie_couleur4,BA.numberCast(double.class, 3))) { 
 BA.debugLineNum = 227;BA.debugLine="afficher_couleur4.Color=Colors.Yellow";
Debug.ShouldStop(4);
main.mostCurrent._afficher_couleur4.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Yellow"));
 };
 BA.debugLineNum = 229;BA.debugLine="If envoie_couleur4=4 Then";
Debug.ShouldStop(16);
if (RemoteObject.solveBoolean("=",main._envoie_couleur4,BA.numberCast(double.class, 4))) { 
 BA.debugLineNum = 230;BA.debugLine="afficher_couleur4.Color=Colors.Green";
Debug.ShouldStop(32);
main.mostCurrent._afficher_couleur4.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Green"));
 };
 BA.debugLineNum = 232;BA.debugLine="comparaison";
Debug.ShouldStop(128);
_comparaison();
 BA.debugLineNum = 233;BA.debugLine="End Sub";
Debug.ShouldStop(256);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			Debug.ErrorCaught(e);
			throw e;
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 20;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 21;BA.debugLine="Dim aleatoire1 As Int";
main._aleatoire1 = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 22;BA.debugLine="Dim aleatoire2 As Int";
main._aleatoire2 = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 23;BA.debugLine="Dim aleatoire3 As Int";
main._aleatoire3 = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 24;BA.debugLine="Dim aleatoire4 As Int";
main._aleatoire4 = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 25;BA.debugLine="Dim compte_couleur1 As Int";
main._compte_couleur1 = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 26;BA.debugLine="Dim compte_couleur2 As Int";
main._compte_couleur2 = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 27;BA.debugLine="Dim compte_couleur3 As Int";
main._compte_couleur3 = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 28;BA.debugLine="Dim compte_couleur4 As Int";
main._compte_couleur4 = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 29;BA.debugLine="Dim envoie_couleur1 As Int";
main._envoie_couleur1 = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 30;BA.debugLine="Dim envoie_couleur2 As Int";
main._envoie_couleur2 = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 31;BA.debugLine="Dim envoie_couleur3 As Int";
main._envoie_couleur3 = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 32;BA.debugLine="Dim envoie_couleur4 As Int";
main._envoie_couleur4 = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 33;BA.debugLine="Dim Bonne_place1 As Int";
main._bonne_place1 = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 34;BA.debugLine="Dim Bonne_place2 As Int";
main._bonne_place2 = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 35;BA.debugLine="Dim Bonne_place3 As Int";
main._bonne_place3 = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 36;BA.debugLine="Dim Bonne_place4 As Int";
main._bonne_place4 = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 37;BA.debugLine="Private couleur_aleatoire1 As Label";
main.mostCurrent._couleur_aleatoire1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 38;BA.debugLine="Private couleur_aleatoire2 As Label";
main.mostCurrent._couleur_aleatoire2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 39;BA.debugLine="Private couleur_aleatoire3 As Label";
main.mostCurrent._couleur_aleatoire3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 40;BA.debugLine="Private couleur_aleatoire4 As Label";
main.mostCurrent._couleur_aleatoire4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 41;BA.debugLine="Private choix_couleur1 As Button";
main.mostCurrent._choix_couleur1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 42;BA.debugLine="Private choix_couleur2 As Button";
main.mostCurrent._choix_couleur2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 43;BA.debugLine="Private choix_couleur3 As Button";
main.mostCurrent._choix_couleur3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 44;BA.debugLine="Private choix_couleur4 As Button";
main.mostCurrent._choix_couleur4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 45;BA.debugLine="Private couleur1 As Label";
main.mostCurrent._couleur1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 46;BA.debugLine="Private couleur2 As Label";
main.mostCurrent._couleur2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 47;BA.debugLine="Private couleur3 As Label";
main.mostCurrent._couleur3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 48;BA.debugLine="Private couleur4 As Label";
main.mostCurrent._couleur4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 49;BA.debugLine="Private afficher_couleur1 As Label";
main.mostCurrent._afficher_couleur1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 50;BA.debugLine="Private afficher_couleur2 As Label";
main.mostCurrent._afficher_couleur2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 51;BA.debugLine="Private afficher_couleur3 As Label";
main.mostCurrent._afficher_couleur3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 52;BA.debugLine="Private afficher_couleur4 As Label";
main.mostCurrent._afficher_couleur4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 53;BA.debugLine="Private Fond As Label";
main.mostCurrent._fond = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 54;BA.debugLine="Private Notice_pion As Label";
main.mostCurrent._notice_pion = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 55;BA.debugLine="Private pion1 As Label";
main.mostCurrent._pion1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 56;BA.debugLine="Private pion2 As Label";
main.mostCurrent._pion2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 57;BA.debugLine="Private pion3 As Label";
main.mostCurrent._pion3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 58;BA.debugLine="Private pion4 As Label";
main.mostCurrent._pion4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 59;BA.debugLine="Private Resulat As Label";
main.mostCurrent._resulat = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 60;BA.debugLine="Private Debug As Button";
main.mostCurrent._debug = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 61;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main_subs_0._process_globals();
starter_subs_0._process_globals();
main.myClass = BA.getDeviceClass ("b4a.example.main");
starter.myClass = BA.getDeviceClass ("b4a.example.starter");
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}