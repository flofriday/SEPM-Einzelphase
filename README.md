# SEPM Einzelphase 2021S

**Punkte**: 62 von 80

**Feedback**:

```
* Gesamtpunkte: 62 
	** Userstories (erreicht): 78 
	** Techstories (Abzüge): -16 
		*** Qualitätsmanager/in: -3 
		*** Usability Engineer: -4 
		*** Technischer Architekt/in: -9 
		*** Datenmanager/in: -0 
* US 3 
	** [-2] Ein Pferd mit Kindern kann nicht gelöscht werden (wird von Validierung verboten) 
* TS 10 
	** [-3] TRACE Log-Level fehlt in mehreren privaten Methoden: getLongObject(), mapRow(), mapTreeRow(), horsesToTree() 
* TS 19 [-4] 
	** Erstellen von Pferd mit zu langem Namen liefert Nachricht BAD_REQUEST 
	** Erstellen von Pferd mit Geburtstag in Zukunft liefert Nachricht BAD_REQUEST 
	** BAD_REQUEST als Response-Nachricht beim Erstellen eines Pferdes mit zu langem Namen bzw. Geburtstag in der Zukunft 
* TS 21 
	** [-5] npm run build wirft viele Fehler 
* TS 24 
	** [-4] Es werden Catch-All Klauseln "catch(Exception e)" an 3 Stellen im HorseEndpoint verwendet. Das ist zu allgemein und kann nicht sinnvoll behandelt werden
```
