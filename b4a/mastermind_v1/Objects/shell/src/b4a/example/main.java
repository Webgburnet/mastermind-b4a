
package b4a.example;

import java.io.IOException;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.PCBA;
import anywheresoftware.b4a.pc.RDebug;
import anywheresoftware.b4a.pc.RemoteObject;
import anywheresoftware.b4a.pc.RDebug.IRemote;
import anywheresoftware.b4a.pc.Debug;

public class main implements IRemote{
	public static main mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public main() {
		mostCurrent = this;
	}
    public RemoteObject getRemoteMe() {
        return remoteMe;    
    }
    
	public static void main (String[] args) throws Exception {
		new RDebug(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
		RDebug.INSTANCE.waitForTask();

	}
    static {
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put("b4a.example.main", "b4a.example.main");
	}

public boolean isSingleton() {
		return true;
	}
     public static RemoteObject getObject() {
		return myClass;
	 }

	public RemoteObject activityBA;
	public RemoteObject _activity;
    private PCBA pcBA;

	public PCBA create(Object[] args) throws ClassNotFoundException{
		processBA = (RemoteObject) args[1];
		activityBA = (RemoteObject) args[2];
		_activity = (RemoteObject) args[3];
        anywheresoftware.b4a.keywords.Common.Density = (Float)args[4];
        remoteMe = (RemoteObject) args[5];
		pcBA = new PCBA(this, main.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _aleatoire1 = RemoteObject.createImmutable(0);
public static RemoteObject _aleatoire2 = RemoteObject.createImmutable(0);
public static RemoteObject _aleatoire3 = RemoteObject.createImmutable(0);
public static RemoteObject _aleatoire4 = RemoteObject.createImmutable(0);
public static RemoteObject _compte_couleur1 = RemoteObject.createImmutable(0);
public static RemoteObject _compte_couleur2 = RemoteObject.createImmutable(0);
public static RemoteObject _compte_couleur3 = RemoteObject.createImmutable(0);
public static RemoteObject _compte_couleur4 = RemoteObject.createImmutable(0);
public static RemoteObject _envoie_couleur1 = RemoteObject.createImmutable(0);
public static RemoteObject _envoie_couleur2 = RemoteObject.createImmutable(0);
public static RemoteObject _envoie_couleur3 = RemoteObject.createImmutable(0);
public static RemoteObject _envoie_couleur4 = RemoteObject.createImmutable(0);
public static RemoteObject _bonne_place1 = RemoteObject.createImmutable(0);
public static RemoteObject _bonne_place2 = RemoteObject.createImmutable(0);
public static RemoteObject _bonne_place3 = RemoteObject.createImmutable(0);
public static RemoteObject _bonne_place4 = RemoteObject.createImmutable(0);
public static RemoteObject _couleur_aleatoire1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _couleur_aleatoire2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _couleur_aleatoire3 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _couleur_aleatoire4 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _choix_couleur1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _choix_couleur2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _choix_couleur3 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _choix_couleur4 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _couleur1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _couleur2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _couleur3 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _couleur4 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _afficher_couleur1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _afficher_couleur2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _afficher_couleur3 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _afficher_couleur4 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _fond = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _notice_pion = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _pion1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _pion2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _pion3 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _pion4 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _resulat = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _debug = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static b4a.example.starter _starter = null;
  public Object[] GetGlobals() {
		return new Object[] {"Activity",main.mostCurrent._activity,"afficher_couleur1",main.mostCurrent._afficher_couleur1,"afficher_couleur2",main.mostCurrent._afficher_couleur2,"afficher_couleur3",main.mostCurrent._afficher_couleur3,"afficher_couleur4",main.mostCurrent._afficher_couleur4,"aleatoire1",main._aleatoire1,"aleatoire2",main._aleatoire2,"aleatoire3",main._aleatoire3,"aleatoire4",main._aleatoire4,"Bonne_place1",main._bonne_place1,"Bonne_place2",main._bonne_place2,"Bonne_place3",main._bonne_place3,"Bonne_place4",main._bonne_place4,"choix_couleur1",main.mostCurrent._choix_couleur1,"choix_couleur2",main.mostCurrent._choix_couleur2,"choix_couleur3",main.mostCurrent._choix_couleur3,"choix_couleur4",main.mostCurrent._choix_couleur4,"compte_couleur1",main._compte_couleur1,"compte_couleur2",main._compte_couleur2,"compte_couleur3",main._compte_couleur3,"compte_couleur4",main._compte_couleur4,"couleur_aleatoire1",main.mostCurrent._couleur_aleatoire1,"couleur_aleatoire2",main.mostCurrent._couleur_aleatoire2,"couleur_aleatoire3",main.mostCurrent._couleur_aleatoire3,"couleur_aleatoire4",main.mostCurrent._couleur_aleatoire4,"couleur1",main.mostCurrent._couleur1,"couleur2",main.mostCurrent._couleur2,"couleur3",main.mostCurrent._couleur3,"couleur4",main.mostCurrent._couleur4,"Debug",main.mostCurrent._debug,"envoie_couleur1",main._envoie_couleur1,"envoie_couleur2",main._envoie_couleur2,"envoie_couleur3",main._envoie_couleur3,"envoie_couleur4",main._envoie_couleur4,"Fond",main.mostCurrent._fond,"Notice_pion",main.mostCurrent._notice_pion,"pion1",main.mostCurrent._pion1,"pion2",main.mostCurrent._pion2,"pion3",main.mostCurrent._pion3,"pion4",main.mostCurrent._pion4,"Resulat",main.mostCurrent._resulat,"Starter",Debug.moduleToString(b4a.example.starter.class)};
}
}