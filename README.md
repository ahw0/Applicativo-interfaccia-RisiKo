# Applicativo interfaccia RisiKo


## Argomenti trattati

1. Uso dell’applicativo
  * Schermata di login e personalizzazione
  * Schermata principale
  * Le sezioni presenti
  	* Nuova partita
    * Riprendi partita
    * Tools
    * Download
2. Giocare una partita
  * Fase preliminare
  * Fase di posizionamento
  * Fase di combattimento
  * Fase di spostamento
3. Otteniamo qualche informazione
  * Ripercorrere una partita
  * Gestione del file log.
4. Altre informazioni reperibili
  * Giocatore
  * Turni
  * Generali

___

## Autori

:it: : Simone Margio


# Guida d’uso dell’applicativo

## Schermata di login e personalizzazione

![1](https://user-images.githubusercontent.com/22590804/27735543-73171ef2-5da0-11e7-86fd-3bf5d1f24b31.jpg)

All’avvio dell’applicativo viene mostrato il menù di login presente in figura, tramite il quale è possibile svolgere le seguenti azioni:

- **[ Login ]**: effettuare l’accesso inserendo l’username e password relativi al database che si vuole utilizzare.
- **[ Opzioni ]**: specificare i parametri d’accesso al database.
- **[ Esci ]**: uscire dall’applicativo.

Premendo sul relativo bottone **[ Opzioni ]** si accede alla seguente schermata:

![2](https://user-images.githubusercontent.com/22590804/27739625-8cf4ec28-5daf-11e7-9591-8a13ddd2c175.jpg)

Da qui è possibile effettuare opportune modifiche per l’accesso al database. Tra queste sono comprese la personalizzazione di: 

-	**Host**
-	**Servizio** 
-	**Porta** 

Una volta modificate le informazioni presenti si può procedere premendo il relativo pulsante **[ Applica ]** per renderle effettive. 
Caso in cui si vogliono ripristinare i parametri d’accesso di default bisognerà premere il pulsante **[ Ripristina predefiniti ]**.  

I parametri predefiniti sono, inoltre, scritti e mostrati in automatico ad ogni apertura della schermata. 
Finite le personalizzazioni l’utente verrà notificato mediante un pop-up contenente tutti i parametri da lui modificati. 

:star: **Feature**: Ogni campo relativo alla personalizzazione d’accesso al database ha opportuni controlli per evitare di inserire stringhe notevolmente lunghe oppure caratteri in campi dove si richiedono solo numeri. 

Una volta ritornati al menù d’accesso si può procedere al login.  L’accesso mediante la correttezza delle credenziali porta l’utente alla schermata principale dell’applicativo (figura sotto), caso opposto verrà notificato un problema d’accesso. 

## Schermata di login e personalizzazione

![3](https://user-images.githubusercontent.com/22590804/27739651-a89b7f50-5daf-11e7-80aa-105c156555cf.jpg)

La schermata principale è il cuore del programma dove l’utente può svolgere tutte le azioni che permettono la gestione di una partita di RisiKo. 
 
Le funzionalità comprendono: 
-	**[ Nuova Partita ]**: creazione di una nuova partita per poter giocare a RisiKo. 
-	**[ Riprendi Partita ]**: riprendere una partita lasciata in sospeso. 
-	**[ Tools ]**: ripercorrere lo svolgimento di una partita e ottenere informazioni aggiuntive sui giocatori o un determinato turno di gioco. 
-	**[ Download ]**: offre la possibilità di scaricare i file contenente il database per poter usufruire dell’applicativo e un popolamento di test. 
-	**[ Esci ]**: uscire dall’applicazione. 
 
L’utente può selezionare premendo sul pulsante apposito per accedere ad una della modalità presenti e alle relative funzionalità. 

## Le sezioni presenti 
### Nuova Partita

![4](https://user-images.githubusercontent.com/22590804/27739861-56a1d1b2-5db0-11e7-8c30-860464585d60.jpg)

Nella creazione di una partita viene chiesto all’utente di specificare vari parametri quali: 
 
-	**Nome partita**: nome da attribuire alla partita. Usato successivamente nella maggior parte delle interazioni in cui l’utente deve specificare la partita di riferimento (es: ripresa di una vecchia partita, ottenere informazioni su in giocatore ed altro). 
-	**N° Giocatori**: selezionabile mediante l’apposito menu a tendina permette di specificare il numero dei giocatori che vogliono partecipare alla partita. 
-	**Nome giocatori**: nome o nickname dei giocatori della partita. 
-	**Colore**: colore scelto dal giocatore con il quale intende giocare. 
A seconda del numero dei giocatori selezionati (minimo tre giocatori) verranno resi disponibili o meno i campi per l’inserimento di tali informazioni. 
 
Una volta pronti è possibile iniziare la partita premendo sul pulsante  **[ Inizia Partita ]**. 

:star: **Feature**: Opportuni controlli evitano l’inserimento di caratteri speciali e spazi che possono portare a un malfunzionamento generale; così come campi lasciati completamente vuoti. Inoltre ogni campo è impostato per accettare una lunghezza massima di caratteri prefissati (nome partita: max 20 caratteri, nome giocatori: max 10 caratteri). 
Per evitare di prendere i valori scelti dall’utente per poi verificarli ed eventualmente informare l’utente sul loro non utilizzo si è preferito affidarsi ad eventi quali Key Pressed e Key Typed creati ad hoc per non accettare caratteri non consoni. 


Tra i controlli presenti, la partita non viene creata quando: 
-	Esiste già una partita con lo stesso nome scelto dall’utente. 
-	Ci sono due o più giocatori con lo stesso nickname (la gestione è case sensitive, ovvero un giocatore “Pippo” è diverso dal giocatore 
“pippo”). 
-	Ci sono due o più giocatori con lo stesso colore. - Sono presenti campi vuoti. 
 
In questi casi l’utente viene informato con un opportuno avviso indicante l’errore commesso. 


### Riprendi Partita

![5](https://user-images.githubusercontent.com/22590804/27739863-56a702e0-5db0-11e7-833e-a61d934589a2.jpg)

Tutte le partite che non hanno ancora un vincitore decretato possono essere riprese mediante questa funzionalità. 
All’utente verrà mostrata la lista di tutte le partite presenti. Scelto il nome della partita esso deve essere scritto nell’apposito campo sotto e successivamente si può procedere premendo il pulsante **[ Riprendi Partita ]**.  

:star: **Feature**: le azioni svolte in una partita si dividono in tre fasi: posizionamento, combattimento, spostamento. Quando si decide di fermare una partita necessariamente ci si trova in una di queste fasi. Alla sua ripresa l’applicativo verifica in base al turno del giocatore (ovvero l’ultimo turno corrispondente al giocatore a cui tocca) in quale delle tre fasi non è presente ancora una “riga” con le informazioni del giocatore. Necessariamente sarà una delle tre, e la prima trovata sarà proprio la fase di gioco da riprendere. 

### Tools 

![6](https://user-images.githubusercontent.com/22590804/27739862-56a6d6a8-5db0-11e7-9d7c-92e5dbad4b1a.jpg)

Permette all’utente di ottenere tutte le informazioni su una partita. In alto a sinistra è presente il riquadro contenente il nome di tutte le partite che sono presenti.  
Scelto il nome della partita e scritto nel campo al posto di “nome partita…” si può procedere a: 
 
-	**[ Ripercorri la partita ]**: vengono mostrate nel riquadro grande, sotto alla scritta “riassunto della partita”, tutte le informazioni passo dopo passo dello svolgimento della partita. Maggiori dettagli su 
“Otteniamo qualche informazione” pagina: 31. 
-	**[ Elimina la partita ]**: eliminazione della partita e di tutte le informazioni salvate su essa. 
-	**[ Altre info ]**: ulteriori informazioni su: giocatori, turni e generali. 
 
Caso in cui si procede a ripercorrere una partita, oltre ad ottenere tutte le informazioni nel riquadro grande verranno mostrate anche alcune informazioni quali: 
 
-	**Stato della partita**: può essere di due tipi: o 	In corso: partita non finita senza alcun vincitore o 	Finita: partita conclusa con un vincitore 
-	**Vincitore**: a seconda dello stato della partita sarà presente il nickname del giocatore vincitore oppure la parola “Nessuno”. 
-	**Totale turni**: valore numerico che esprime il numero di turni a cui si è arrivati durante la partita. 
-	**Totale combattimenti**: valore numerico che esprime il numero di combattimenti che sono avvenuti durante la partita. 

### Download

![7](https://user-images.githubusercontent.com/22590804/27739860-56a05d8c-5db0-11e7-93c5-5cad62fc4c4b.jpg)

Area dove l’utente può scaricare i file/script: 
-	**[ Database ]**: file per procedere con la creazione del database in base alle regole del gioco RisiKo! 
-	**[ Popolamento ]**: file che mostra un esempio di popolamento da poter eseguire nel database. 

:heavy_exclamation_mark:
:point_right: Ogni partita nel database è registrata mediante un codice numerico, ovvero valori interi che partono da 1 a N. Ad esempio la partita “MulinoBianco” ha un codice pari a 4, mentre la partita “Frollino” ha un codice pari a 5.  
Nel caso in cui si scarichi il file di popolamento, il valore alla partita ad esso associato è pari a 1. Questo comporta che se è già presente una partita con tale valore associato, lo script del popolamento non verrà eseguito. 
La scelta del valore 1 è derivata dalla motivazione che lo script del popolamento venga eseguito quando il database è “nudo e crudo”, ovvero proprio come file di esempio usato prima di procedere a qualsiasi altro inserimento tramite database e/o applicativo. 


# Giocare una partita
## Fase preliminare

![1](https://user-images.githubusercontent.com/22590804/27741493-9b4aecae-5db5-11e7-8983-0b8e21e09e7b.jpg)

Una volta creata una partita l’utente si ritroverà in una schermata simile a quella nella figura mostrata sopra. 

Tale schermata racchiude la fase preliminare di gioco.  
 
La fase prevede l’inserimento di un numero di armate prefissato (indicato nella schermata sotto “N° armate max da inserire”) in uno o massimo tre territori appartenenti al giocatore. 

Nella parte in alto è possibile visualizzare informazioni quali: 
-	**Tocca a**: mostra il nickname del giocatore a cui tocca svolgere l’azione, in questo caso l’inserimento di armate. - Il tuo ID: identificativo associato al giocatore. 
-	**Partita N°**: identificativo associato alla partita/numero della partita. 
-	**Nome Partita**: nome che è stato dato dall’utente alla partita. 
-	**N° armate max da inserire**: sono il numero massimo di armate che il giocatore può inserire in uno o più territori. 
-	**N° armate totali**: sono il numero di armate che restano ancora da inserire. 
In basso a destra si trova invece: 
-	**N° turno**: valore numerico del turno di gioco. 

***Maggiori dettagli su N° turno***: il gioco ruota attorno ai turni, espressi in questo caso tramite valori numerici da 1 a N. In ogni turno c’è sempre un giocatore ad esso associato (esempio al turno n°3 c’è associato “Simone”, il n°4 “Pluto” e così via). In parole semplici il turno corrisponde al “a chi tocca giocare ora?”. 

A sinistra, invece, vengono indicati i territori **(I tuoi territori)** del giocatore e il numero di armate presenti in esso **(Carri sul territorio)**. Inizialmente ogni territorio avrà un’unica armata a presidiare.  

Le informazioni inerenti al: **Il tuo ID**, **Partita N** e **N° turno** possono tranquillamente essere trascurate dal giocatore (sono presenti per verificare e visualizzare la corretta continuità della partita) in quanto le restanti informazioni bastano per poter inserire le armate. 

L’ultima parte, a destra, seguita dalla scritta **“Posizionamento”** è formata da tre parti: 
-	**Nome territorio**: va inserito il nome del territorio dove si vogliono aggiungere le armate. 
-	**N° armate**: valore numerico di armate che si vogliono inserire sul territorio espresso in precedenza. 
-	**Abilita territorio**: se il giocatore decide voler inserire delle armate in un altro territorio, può abilitare tale inserimento spuntando la casella ad esso relativa. 

***Maggiori dettagli su abilita territorio***: fino ad esaurimento delle armate da poter inserire, come da regolamento, ogni giocatore deve disporre al massimo 3 armate su ogni territorio di sua appartenenza. 
Per motivi strategici un giocatore può ad esempio posizionare 2 armate sul territorio A mentre 1 la posiziona sul territorio B. 
Per effettuare tali posizionamenti, il giocatore scriverà A nel primo campo sotto a “Nome territorio” e il valore 2 nel campo affianco, successivamente dovrà spuntare la seconda (o terza) checkbox per poter scrivere il territorio B e il valore 1. 
Massimo tre territori possono essere abilitati in quanto al massimo possono essere inserite 3 armate in tre territori (1 armata su ciascun territorio). 

:star: **Feature**: In ogni campo vengono vietati i copia-incolla di informazioni per evitare che si vada a copiare qualche carattere speciale che possa danneggiare il successivo inserimento nel database. Inoltre opportuni controlli verificano che il nome del territorio sia coretto e che appartenga al giocatore. Certo non vogliamo che rendi più forti i nemici regalando loro armate! 

Scelto almeno un territorio e il numero di armate da inserire si può procedere premendo sul pulsante **[ Inserisci ]**. 

La fase preliminare dura finché tutti i giocatori hanno ancora armate da aggiungere. Una volta che ogni giocatore ha finito il numero di armate si entra nel vivo del gioco. 

:star: **Feature**: Si parte sempre dal turno numero 1, in realtà esiste anche il turno 0.  
Questo è usato dal database per comprendere che i giocatori che vogliono partecipare alla partita sono finiti e che si può passare alla fase successiva di preparazione. 
In tale fase il database randomizza i territori e li assegna ai giocatori (posizionando 1 armata su ogni territorio), assegna gli obiettivi e stabilisce il giro che i giocatori devono fare (proprio per questo non è detto che il giocatore di id numero 1 sia il primo a cominciare).  

## Fase posizionamento

![2](https://user-images.githubusercontent.com/22590804/27741496-9b54ac1c-5db5-11e7-92dd-8e92eb11542e.jpg)

Terminata la fase preliminare inizierà la fase vera e propria di gioco. Essa viene divisa in tre parti: 
 
-	**Posizionamento**: il giocatore ha diritto a ogni turno l’inserimento di N armate (calcolate in base ai suoi territori o tramite una combinazione di carte) nei suoi territori. 
-	**Combattimento**: si può decidere o meno se attaccare i territori nemici con le proprie armate. 
-	**Spostamento**: si può decidere o meno se spostare un certo numero di armate da un proprio territorio ad un altro. 
La schermata della fase di posizionamento contiene in parte le medesime informazioni già viste.

Vi si aggiungono: 
-	**N° armate max che si possono inserire**: valore numerico che esprime il numero esatto di armate che il giocatore può inserire in uno o più dei suoi territori. 
-	**Tipo inserimento**: il giocatore può scegliere come ottenere il numero di armate specificando il tipo di inserimento. Si divide in: 
inserimento classico e inserimento combinazione carte. 
-	**Le tue carte**: le carte che ha in mano il giocatore. Per comodità vengono espresse mediante una singola lettera (C – Cavalleria, F – Fante, A – Artigliere, J – Jolly).  
-	**Il tuo obiettivo**: viene indicato l’obiettivo che è stato affidato al giocatore. Se un giocatore raggiunge il proprio obiettivo viene dichiarato vincitore, concludendo la partita. 
-	**Territori confinanti**: per motivi strategici il giocatore di turno può inserire un qualsiasi nome territorio (suo e non) nel campo sotto la scritta “Nome territorio” e premendo il pulsante [ Mostra territori confinanti ] verranno mostrati tutti i territori confinanti a quello scelto. 

***Maggiori dettagli su N° armate max che si possono inserire***: esprime come detto il numero massimo di armate che il giocatore può inserire.  Tale valore viene può essere calcolato in due modi in base alla scelta del giocatore. 


![3](https://user-images.githubusercontent.com/22590804/27741495-9b538bac-5db5-11e7-875c-32ae660e70e4.jpg)


Come mostrato le opzioni si dividono in: 
-	**Inserimento classico**: il numero di armate viene calcolato da quanti sono i territori occupati dal giocatore diviso per tre. Inoltre se il giocatore occupa tutti i territori di uno o più continenti ha diritto a un numero di armate supplementari. 
-	**Inserimento combinazione carte**: include il numero di armate calcolato dall’inserimento classico più ulteriori armate supplementari date dalla combinazione di carte usata dal giocatore. 
 
***Nota: sul numero di armate leggere il regolamento del gioco.*** 
 
A seconda della scelta del tipo inserimento il valore di “N° armate max che si possono inserire” varia.  

![4](https://user-images.githubusercontent.com/22590804/27741497-9b569ea0-5db5-11e7-981f-106dc493c904.jpg)


L’esempio sopra mostra come la scelta di un inserimento classico abbia dato al giocatore un inserimento di massimo 4 armate. 
 
Caso in cui, invece, viene scelto inserimento combinazione carte, si abiliterà il pannello sotto la scritta “Carte” come mostrato. 

![5](https://user-images.githubusercontent.com/22590804/27741494-9b4c2754-5db5-11e7-9110-3bb6b22b0f8e.jpg)


Il giocatore può scegliere quale combinazione di carte utilizzare in base alle carte che ha a disposizione (sotto la casella: “Le tue carte”). Per facilitare la lettura vicino a ogni combinazione è presente la combinazione di lettere che il giocatore deve possedere. Ad esempio se il giocatore ha le seguenti carte: A – A – C – F – A, può scegliere di usare la combinazione FCA oppure la combinazione AAA. 
Una volta selezionata la giusta combinazione basterà premere sul pulsante  **[ Usa combinazione ]** e in automatico verranno aggiornate il numero di armate massiche che si devono inserire. 


:star: **Feature**: Una carta di gioco è composta da due informazioni: territorio e simbolo. Il territorio è uno dei territori presenti nella mappa di gioco mentre il simbolo può essere uno dei caratteri: C,F,A,J. Il regolamento del gioco prevede che se il giocatore nella combinazione di carte usata possiede anche il territorio di una o più carte, ha diritto ad un supplemento di ulteriori 2 armate. 
Allora cosa succede se il giocatore ha in mano delle carte tipo: F – C – A – A, dove solo una delle A è associata anche a un territorio che lui possiede?  
Siccome la combinazione prevede F – C – A, quali delle due A viene presa per formare tale combinazione? 
La risposta più ovvia è considerare la A che comprende anche il territorio posseduto dal giocatore, così da avere un numero ancora maggiore di armate da inserire. 
Ed è anche la risposta data dal database. Infatti il database inizia la ricerca della migliore carta A, ovvero quella che comprende anche un territorio posseduto dal giocatore. 
Quindi in questo caso tra le due A verrà selezionata quella che permette di avere 2 armate supplementari. 


Una volta scelto un tipo di inserimento verrà abilitato il pulsante **[ Inserisci carri ]**.  
Il giocatore può quindi procedere a inserire il nome di un territorio da lui posseduto (visibile nella schermata “I tuoi territori”) nell’apposito campo sotto a “Nome territorio”. 
Successivamente può scegliere il numero di armate che si vuole posizionare sul territorio scrivendo nel campo al di sotto si “N° armate”. Infinite basta premere il pulsante **[ Inserisci carri ]** per effettuare l’inserimento. Il numero di armate verrà aggiornato istantaneamente nella schermata “Carri sul territorio” relativo al territorio scelto.  
Il giocatore può anche decidere di inserire le armate che ha a disposizione in più territori. 
Una volta inserite un numero di armate inferiore al numero massimo di armate che possono essere inserite; basta eliminare il nome del primo territorio scritto e inserire un nuovo nome territorio con le relative armate. 
 
Una volta inserite tutte le armate date si passa alla fase successiva. 


## Fase di combattimento 

![1](https://user-images.githubusercontent.com/22590804/27741814-a5ac8d82-5db6-11e7-9ec9-5c43afacffd5.jpg)

Sia la fase di combattimento che la fase successiva di spostamento rientrano nelle fasi opzionali. Il giocatore è libero di scegliere di non compiere nessun combattimento o spostamento. 
 
In questa fase è presente in basso al centro il pulsante **[ Finisci i combattimenti ]**. 
Tale pulsante può essere utilizzato per: 
-	Passare alla fase successiva senza aver effettuato nessun combattimento. Semplicemente va premuto una volta che appare la schermata di combattimento. 
-	Passare alla fase successiva dopo aver effettuato N combattimenti. 
 
Come nelle schermate precedenti sono presenti informazioni già descritte.
Per descrivere al meglio le rimanenti informazioni e l’intera schermata, seguiamo passo dopo passo un possibile combattimento. 
 
Il giocatore di turno decide di voler effettuare un combattimento. Il territorio da cui far partire l’attacco sarà l’Ontario. Questo viene scritto  
nella casella sotto “Nome territorio attaccante” come mostrato nella figura. 
 
![2](https://user-images.githubusercontent.com/22590804/27741815-a5adc6e8-5db6-11e7-8df9-4db0896b19e2.jpg)

Siccome il giocatore non conosce quali siano i territori nemici che confinano con il territorio d’attacco, decide di premere il pulsante **[ Mostra confini nemici ]** ottenendo a destra le informazioni su: 
-	**Territori nemici confinanti**: i nomi, se presenti, di tutti i territori non posseduti dal giocatore che confinano con il territorio da lui espresso nel campo “Nome territorio attaccante”. 
-	**Carri sul territorio**: carri nemici presenti sul territorio. 
-	**Colore**: colore del nemico, utile per fini strategici. 

![3](https://user-images.githubusercontent.com/22590804/27741816-a5b17b30-5db6-11e7-801c-6672065057c4.jpg)

Si decide di attaccare il territorio Alberta scrivendo il nome nella casella sotto a “Nome territorio attaccato”, come mostrato. 

![4](https://user-images.githubusercontent.com/22590804/27741817-a5b501b0-5db6-11e7-8dc8-1d2f52002a0d.jpg)

Una volta scelti il territorio d’attacco e quello d’attaccare siamo pronti all’attacco! 
Il giocatore preme il pulsante **[ Prepara all’attacco ]**. 
 
Si passa alla seconda sotto fase del combattimento, il lancio dei dadi. 
Come si può vedere dalla figura sotto una volta premuto il bottone **[ Prepara all’attacco ]** verranno aggiornate le informazioni presenti in alto a destra della schermata: 
-	**Nickname difensore**: è il nickname del giocatore che si sta attaccando. 
-	**ID difensore**: l’id del difensore che è stato assegnato. 

![5](https://user-images.githubusercontent.com/22590804/27741818-a5b5a638-5db6-11e7-8f89-f84e399cff37.jpg)

Per lanciare i dadi bisogna prima specificare il numero di dadi da lanciare.  Ciò può essere fatto mediante il menu a tendina presente sia per il giocatore attaccante che per quello che si difende (figura sotto). Il massimo numero di dadi che possono essere lanciati da entrambi i giocatori è 3. 
Ovviamente il numero di dadi da usare dipende sia dalle strategie usate dai giocatori che dal numero di armate presenti in entrambi i territori. Infatti ogni dado è associato a una armata.  
Se il giocatore attaccante possiede 2 armate non può lanciare 3 dadi così come non può lanciarne 2 (si ricordi che secondo le regole di gioco sul territorio attaccante deve esserci sempre 1 armata a presidiare, quindi avendo 2 armate non sarà possibile effettuare un lancio di 2 dadi in quanto in caso di sconfitta si perderebbe il territorio, contravvenendo al regolamento). 

![6](https://user-images.githubusercontent.com/22590804/27741820-a5c6c60c-5db6-11e7-8510-3d5b9a9c56f2.jpg)


Selezionato il numero di armate verranno abilitate le caselle sottostanti dove andranno inseriti i valori dei dadi lanciati. 
Qui sotto un esempio: 

![7](https://user-images.githubusercontent.com/22590804/27741819-a5c678dc-5db6-11e7-8fd7-bb5942776ad1.jpg)

Quando è tutto pronto il giocatore non deve fare altro che premere il pulsante **[ Attacca ]** per dare inizio al combattimento. 
E’ possibile anche decidere di non voler più attaccare il territorio stabilito, in questo caso basta premere su **[ Cambia territorio da attaccare ]**. 
In questo caso si decide si attaccare. Una volta premuto il pulsante **[ Attacca ]** il giocatore verrà notificato sull’esito del combattimento, ottenendo le informazioni sul numero di armate perse (sia dell’attaccante che del difensore). 

![8](https://user-images.githubusercontent.com/22590804/27741821-a5c9c3ca-5db6-11e7-926f-00fa3a1f1c4a.jpg)

L’esito di tale combattimento ha riportato che il difensore ha perso 1 carro. Ciò è dovuto in quanto è stato preso il valore più alto lanciato dal dado dell’attaccante (5) ed è stato confrontato con il valore più alto del difensore (1), portando quindi il difensore a perdere un numero di carri pari al numero di dadi lanciati. 
 
Terminato il combattimento il giocatore può: 
-	Decidere di attaccare nuovamente semplicemente scrivendo il valore dei nuovi dadi lanciati e ripremendo il pulsante **[ Attacca ]**. 
-	Cambiare il territorio da attaccare premendo **[ Cambia territorio da attaccare ]**. 
-	Finire i combattimenti premendo **[ Finisci i combattimenti ]**. 
 
Supponiamo di voler proseguire l’attacco finché nel territorio Alberta non restano più armate per difenderlo. 
In questo caso il giocatore attaccante conquisterà il territorio e apparirà una notifica del tipo: 

![9](https://user-images.githubusercontent.com/22590804/27741822-a5d06b4e-5db6-11e7-9016-d63aacff6671.jpg)

Conquistato il territorio, come scritto dalla notifica, vengono spostate N armate quanto il numero di dadi lanciato. 
Il giocatore però, può decidere anche di spostare tutte le armate (eccetto una che deve rimanere a presidiare) dal territorio attaccante a quello conquistato. Per farlo basterà premere sul pulsante in basso **[ Sposta tutte le armate ]**. 
 
Avendo conquistato il territorio Alberta, lo si ritroverà all’interno dello  elenco “I tuoi territori” con il numero di armate stabilito (in questo caso l’attacco è stato effettuato usando 2 dadi e quindi sono state spostate 2 armate). 

![10](https://user-images.githubusercontent.com/22590804/27741864-c857f524-5db6-11e7-9bdf-25878f6faa12.jpg)

***Ulteriori informazioni***: ad ogni territorio conquistato viene verificato se il giocatore difensore abbia perso proprio l’ultimo territorio che aveva.  Caso positivo viene notificato all’utente che il giocatore difensore ha perso.  Allo stesso modo viene verificato che il giocatore attaccante, conquistato un nuovo territorio, abbia raggiunto il suo obiettivo.  
Caso positivo viene notificato all’utente che il giocatore attaccante ha vinto la partita e si viene riportati al menu principale. 
 
Finiti i combattimenti si decide di passare alla fase successiva premendo sul pulsante **[ Finisci i combattimenti ]**. 


:star: **Feature**: La fase di combattimento è quella che ha più controlli e chiamate al database rispetto a tutte le altre schermate dell’applicativo. Tra i tanti controlli si va a verificare ad esempio: se il giocatore ha il territorio da cui far partite l’attacco, se il territorio d’attacco confina con quello da attaccare, se entrambi i territori non appartengono allo stesso giocatore, se è presente più di 1 armata nel territorio d’attacco e tanti altri. Lo stesso vale per il lancio dei dadi, così come la chiamata a opportune funzioni del database per la verifica dell’esito del combattimento, o per la verifica della vittoria del giocatore o della sconfitta del giocatore difensore. 



## Fase spostamento  

![1](https://user-images.githubusercontent.com/22590804/27742209-14cdf1a0-5db8-11e7-8c6e-c8f4f8aabd85.jpg)

Si è arrivati all’ultima fase di gioco che conclude il turno del giocatore, lo spostamento. 
Come nella fase di combattimento, anche qui è possibile scegliere se effettuare uno spostamento di armate oppure non farlo premendo semplicemente sul pulsante **[ Non effettuare nessuno spostamento ]**. 


Nel caso in cui si voglia spostare un certo numero di armate da un territorio ad un altro, bisogna inserire il nome del territorio di partenza nella casella sotto “Nome territorio di partenza”. 
 
Siccome uno spostamento può avvenire solo tra territori confinanti appartenenti allo stesso giocatore del turno in corso, premendo sul pulsante **[ Mostra confini ]** verranno mostrati, se presenti, i territori confinanti che appartengono al giocatore dove è possibile inviare le armate. In questo caso il territorio Venezuela del giocatore ha come territorio confinante appartenente al giocatore stesso il Brasile. 

![2](https://user-images.githubusercontent.com/22590804/27742210-14d2cd42-5db8-11e7-939d-113df6daabdc.jpg)

Scelto il territorio in cui spostare le armate e scritto nel riquadro sotto 
“Nome territorio d’arrivo” non resta che specificare il numero di armate da spostare in “N° armate da spostare”. 
 
Fatto tutto basterà premere sul pulsante sposta armate per ricevere la notifica del coretto spostamento. 

![3](https://user-images.githubusercontent.com/22590804/27742211-14d7e53e-5db8-11e7-93a1-77715055ce41.jpg)

Con questo si conclude l’ultima fase del turno del giocatore e si ripartirà dalla prima fase “Posizionamento” con il giocatore successivo. 
 
Il giro quindi creato “Posizionamento – Combattimento – Spostamento” verrà interrotto solo se: 
-	Viene proclamato un vincitore. 
-	Si decide di mettere in pausa la partita premendo sull’icona X della schermata in cui vi si trova. 

## Otteniamo qualche informazione 

###	Ripercorrere una partita 

Come già visto è possibile reperire passo dopo passo ogni singola mossa che si è svolta in una partita. 
 
Sotto è presente un esempio di ciò che può essere visto scegliendo il nome di una partita e premendo sul pulsante **[ Ripercorri la partita ]**. 

![4](https://user-images.githubusercontent.com/22590804/27742208-14cb6a7a-5db8-11e7-9aed-a03f4c4712e5.jpg)

Le informazioni che vengono mostrate nel riquadro grande sotto alla scritta 
“Riassunto della partita” sono reperite dal file presente nel percorso principale dove viene avviato l’applicativo. 

### Gestione del file Log

La gestione del file avviene nella seguente maniera: 
 
Il file viene generato alla creazione della partita prendendo il nome della stessa e aggiungendo il testo ***“_log.txt”*** come identificativo.  
Di norma quando viene creata una partita non è possibile che esista già un file con il nome della partita; questo perché porterebbe ad avere nel database una partita con lo stesso nome di quella che si vuole creare, portando quindi alla non creazione della partita e, quindi, del nuovo file. Nel caso in cui sia stato generato un file che ha lo stesso nome della partita che si sta creando e tale nome non è presente nel database, il file viene sovrascritto. 
 
Durante lo svolgimento di una partita vengono prese tutte le informazioni pertinenti atte alla ricostruzione passo dopo passo di essa.  
Tutte le informazioni vengono convertite in una stringa ed inviate a una apposita funzione che “apre” il file, inserisce la stringa alla fine e lo 
“chiude”. 
 
L’insieme di tutte le stringhe permette di ottenere un unico file dove sono presenti informazioni riguardanti: 
-	Informazioni riguardanti la creazione della partita. Include la data, numero di giocatori presenti, nickname e colore scelto. 
-	Tutti i posizionamenti svolti nelle fasi: preliminare, posizionamento, combattimento e spostamento. Include territori, numero di armate, azioni svolte. 
-	Tutti i combattimenti svolti. Include il numero di dadi lanciati, esito del combattimento, armate perse. 
-	Ulteriori informazioni su: momenti in cui la partita è stata messa in pausa, ripresa della partita, giocatori perdenti, giocatore vincente. 
 
Il file viene cancellato nel caso in cui si procede alla eliminazione della partita premendo sul pulsante **[ Elimina la partita ]**. 

:question: Alcune domande:

**Q**: Cosa succede se eliminando una partita il file è stato già eliminato in precedenza? 

**A**: Viene notificato all’utente che probabilmente il file è già stato eliminato ma per evitare eventuali problemi di provvedere, se presente, alla sua eliminazione. 
 
**Q**: Se durante lo svolgimento di una partita si elimina il file, cosa succede? 

**A**: Prima di scrivere qualsiasi cosa sul file viene sempre verificata la sua esistenza. 
Caso in cui si il file non è più presente allora verrà ricreato, inserendo all’interno una notazione che informa l’utente che tutte le vecchie informazioni sono andate perse, successivamente si continua a scrivere nel file tutte le informazioni della partita. 
 
**Q**: Il file contiene tutte ma proprio tutte le informazioni? 

**A**: Ci sono alcune informazioni che non sono presenti nel file ma si ottengono accedendo tramite il pulsante [ Altre info ]. Informazioni mancanti sono: valori dei dadi lanciati, obiettivo assegnato ai giocatori, carte che i giocatori hanno in mano. 
 
**Q**: Se viene creato manualmente il file prima della creazione della partita stessa?

**A**: Quando la partita viene creata il file creato manualmente viene sovrascritto. 

## Altre informazioni reperibili


![1](https://user-images.githubusercontent.com/22590804/27742567-7d486a8e-5db9-11e7-8122-0ff10b94ba10.jpg)

E’ possibile ottenere delle informazioni addizionali accendo alla schermata mostrata in foto tramite il pulsante **[ Altre info ]** presente nella schermata “Ripercorre una partita”. 
 
Tutte le informazioni reperibili, a differenza del file, sono ottenute interrogando direttamente il database. 

Tra queste troviamo: 
-	**Giocatori**: selezionata la partita e un giocatore vengono fornite informazioni di vario genere riguardante il giocatore. Ad esempio: tutti i territori conquistati, le carte che ha in mano, le armate totali che ha ancora in gioco ed altre. 
-	**Turni**: selezionata la partita e un turno vengono mostrate tutte le azioni svolte nelle tre fasi (posizionamento, combattimento, spostamento) dal giocatore del turno scelto. 
-	**Generali**: comprende una raccolta generica di informazioni di carattere generale. Ad esempio: numero di partite presenti, numero di tutti i combattimenti svolti di tutte le parte, il territorio preferito dai giocatori ed altri. 

### Informazioni su: Giocatore 

![2](https://user-images.githubusercontent.com/22590804/27742568-7d52ba20-5db9-11e7-8ec5-79d233ccea2d.jpg)

La schermata sulle informazioni del giocatore può essere divisa in due parti. 
La parte sinistra (che includono le scritte “Passo 1”, “Passo 2”) dove viene richiesta l’interazione dell’utente e la parte destra dove verranno mostrate tutte le informazioni. 
 
Analizzando la parte sinistra bisognerà partite dal “Passo 1” e specificare il nome della partita da cui reperire le informazioni. Una volta scelta la partita, essa andrà scritta nel campo apposito sotto la scritta “Inserisci il nome della partita”. 
Una volta fatto bisognerà premere il pulsante **[ Mostra i giocatori ]**. 
 
Si passerà quindi al “Passo 2” dove nel riquadro in basso saranno presenti i nickname di tutti i giocatori della partita scelta. 

![3](https://user-images.githubusercontent.com/22590804/27742570-7d581448-5db9-11e7-9f30-db166a05b507.jpg)

Bisognerà quindi scegliere il nickname di un giocatore e inserirlo nel campo sotto la scritta “Inserisci il nome del giocatore”. 
 
Per ottenere le informazioni basterà premere il pulsante **[ Mostra info ]**. Invece se si vuole cambiare la partita per ottenere informazioni su altri giocatori bisognerà premere il pulsante **[ Cambia partita ]** e ripetere il procedimento partendo dal “Passo 1”. 
 
Una volta premuto il pulsante **[ Mostra info ]** si abiliteranno tutti i campi nella parte destra della schermata, come mostrato dalla figura in basso. 

![4](https://user-images.githubusercontent.com/22590804/27742569-7d562930-5db9-11e7-9206-058fc349a430.jpg)

Tra i campi presenti troviamo: 
-	**Nickname**: nome del giocatore selezionato. 
-	Colore: colore scelto del giocatore. 
-	**ID conferito**: valore numerico che è stato attribuito dal database al giocatore. 
-	**N° armate in gioco**: numero di armate che il giocatore ha in tutti i suoi territori. 
-	**Obiettivo**: obiettivo assegnatogli. 
-	**Carte che ha in mano**: simbolo delle carte che il giocatore può usare. 
(per comodità i simboli sono mostrati nella loro interezza). 
-	**N° combattimenti fatti**: numero dei combattimenti che il giocatore ha effettuato nel corso della partita. 
-	**Quante volte ha tirato il dado**: numero di quante volte, nel corso della partita, il giocatore ha lanciato i dadi. 
-	**N° territori conquistati**: numero dei territori che è riuscito a conquistare distruggendo le armate nemiche. 
-	**Combattimenti effettuati**: vengono mostrati in coppia il territorio da cui è stato fatto partire l’attacco e il territorio attaccato. 
-	**Territori conquistati**: nome di tutti i territori conquistati. 
-	**Territori ordinati in base al numero di armate**: vengono elencati tutti i territori appartenenti al giocatore dando priorità ai territori con più carri. 
-	**Giudizio finale**: in base ad alcuni parametri viene generato un giudizio per dare eventuali consigli al giocatore o incoraggiarlo a raggiungere il suo obiettivo. 


### Informazioni su: Turno  

![5](https://user-images.githubusercontent.com/22590804/27742571-7d693534-5db9-11e7-94da-1ad71bb3dba5.jpg)

Come già spiegato anche questa schermata può essere divisa in due parti. 
 
L’utente dovrà scegliere e scrivere il nome della partita al “Passo 1” e premere sul bottone **[ Mostra i turni ]**. 
Successivamente al “Passo 2” verranno mostrati tutti i turni che si sono svolti durante il corso della partita con i relativi nickname dei giocatori associati ad esso. 
 
La figura in basso mostra un esempio. 

![6](https://user-images.githubusercontent.com/22590804/27742572-7d713c16-5db9-11e7-894a-8bc4197f04f3.jpg)

Una volta scelto il turno bisognerà scrivere il valore numerico nel campo sotto la scritta “Inserisci il numero del turno”. 
 
Premendo su **[ Mostra info ]** verranno visualizzate tutte le informazioni nella parte destra della schermata. Un esempio è riportato nella pagina successiva. 
 
Invece se si vuole cambiare partita per ottenere informazioni su altri turni di gioco si dovrà premere **[ Cambia partita ]**. 
 
![7](https://user-images.githubusercontent.com/22590804/27742573-7d75379e-5db9-11e7-811d-112d9d802ea3.jpg)

Tra i campi presenti troviamo: 
-	**N° turno**: numero del turno che è stato scelto 
-	**Giocatore**: nickname del giocatore che ha svolto le azioni nel turno scelto. 
-	**N° di posizionamenti effettuati**: valore numero dei posizionamenti dei carri effettuato dal giocatore in quel turno. 
-	**Somma delle truppe inserite**: se il giocatore effettua più posizionamenti, verranno sommate tutte le truppe che ha inserito. 
-	**Altre informazioni (Posizionamento)**: vengono mostrati i territori, numero di armate, e tipo di posizionamento (preliminare – classico – tramite combinazione carte) che sono stati effettuati. 
-	**N° combattimenti effettuati**: numero dei combattimenti svolti durante il turno. 
-	**Altre informazioni (Combattimento)**: vengono mostrati il territorio d’attacco e il territorio che si difende, tutte le combinazioni di dadi che sono stati lanciati e l’esito del combattimento (se ha portato o meno alla conquista del territorio). 
-	**N° di spostamenti effettuati**: numero degli spostamenti (compreso tra 0 – 1 in quanto lo spostamento è opzionale oppure ne può essere fatto solo uno). 
-	**Altre informazioni (Spostamento)**: viene visualizzato lo spostamento effettuato dando informazioni sul territorio di partenza, territorio d’arrivo e numero di armate spostate. 

### Informazioni su: Turno  

![8](https://user-images.githubusercontent.com/22590804/27742574-7d7f70ba-5db9-11e7-8b72-b80eef02109d.jpg)

La schermata ***“Generali”*** mostra informazioni a livello generale prendendo in considerazione tutte le partite presenti. 
 
Tra i campi presenti troviamo: 
Numero totale di: mostra la somma di tutte le informazioni di tutte le partite prese insieme. 
 
-	**Partite presenti**: partite che sono state create. 
-	**Giocatori presenti**: tutti i giocatori. 
-	**Dadi tirati**: numero totale di tutti i dadi che sono stari tirati nel corso delle partite. 
-	**Armate**: tutte le armate presenti in tutti i continenti. 
-	**Turni di gioco**: i turni giocati. 
-	**Combattimenti/Posizionamenti/Spostamenti**: totale di tutte le fasi di gioco effettuate. 
 
**Numero totale di inserimenti**: mostra la somma delle informazioni sulla tipologia di inserimenti che ogni giocatore ha effettuato. 
-	**Preliminari**: maggiori informazioni pag. 13. 
-	**Classici/Combinazione carte**: maggiori informazioni pag. 18. 
 
Territori: sono presenti informazioni sui territori. 
-	**Territorio preferito dai giocatori**: mostra il territorio che contiene più armate di tutte le partite presenti. 
-	**Territorio più trascurato**: mostra il territorio con il minor numero di armate di tutte le partite. 
-	**Territorio più attaccato**: mostra il territorio che ha subito più attacchi nelle fasi di combattimento. 
 
**Altre**: informazioni su carte e obiettivi. 
-	**Combinazione di carte più usata**: mostra la combinazione di carte più usata dai giocatori di tutte le partite. 
-	**L’obiettivo più presente**: mostra l’obiettivo che è stato assegnato maggiormente ai giocatori. 
