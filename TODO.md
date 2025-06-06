# TODO

## UML
- [x] : Classe dé
- [ ] : Compléter les classes avec les méthodes qui manquent
- [x] : Bouger Initiative
- [x] : Classe equipement, mettre getArme(), getArmure()
- [x] : Faire les étiquettes d'affichage, ou les mettres? * pour les équipement?
- [x] : methode getEtiqeutte() dans en  tité
- [x] : ou mettre m_ordre? tour ou partie?
- [x] : creer package partie
- [ ] : signature de methode d'interface dans classe interface?
~~- [ ] : mettre en static plein de truc, quoiestqui, quiestla,~~ Obsolète
~~- [x] : changer nom package objet par objetDeJeu~~ Obsolète
- [ ] : faire des classes pour l'affichage, pour les différentes partie de l'affichage
- [ ] : refaire les relation au propre
- [ ] : comment on recupere les bonus des classes/race
- [ ] : faire une mehose prendre et un s'équiper
- [ ] : faire une classe factory pour créer les instances et les trucs "prédéfinis"
- [ ] :

## Code
- [ ] : creer le string de l'affichage, map + info
- [x] : methode calcul distance
- [x] : ajout la portée au arme
- [x] : enlever arme legere et ajouter arme a distance
- [ ] : ajout méthode factory aux armes pour avoir des armes prédef 
- [ ] : pour les degat des armes, une interface qui appel lancerDées avec es bons arguments
         quand perso attaque : perso.attaque(case, this.armeEquipee.jetDegat())
- [ ] : dans carte, il faut quand on deplace qqchose tester si il n'y a pas un obstacle dans la case
- [ ] : finir condition du while dans constructeur partie, deroulement des tours
- [ ] : pour afficher le contenu d'une case dasn afficahgeCarte: mettre deux list dans case, une pour les gameObject et une autre pour les object, il faudra aussi deux méthode pour ajouter
- [ ] : Faire une enum avec un gettype() qui retourne une valeur de l'enum pour les items etc.
- [ ] :


## Question prof

deroulement de la partie dans constructeur de partie? ou faut il faire une méthode déroulementDeLaPartie?

atribut dans arme, protected ou private?
alors osef, mettre methode lancerdés dans arme, et mettre attribut en private

attribut arme et armure, static?








void main (){

    demarrerPartie(carteParDefaut, 15, 15)
    demarrerPartie( opt = partie par defaut, maxX, maxY)
    
       
       boucle 15 itérations
        tour 1: foreach GameObject entité in m_ordre
                pour un perso
                    utilisateur tape: attaquer /n 10 5
                dans le code: partie.
                                    tour[1].
                                            (attaque(entité, quoiEstIci(10,5)))
                dans le code: partie.
                                    tour[1].
                                            (entité.attaque (entité, quoiEstIci(10,5)))
                                            
}                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
pseudocode
attaque()
    '   test que personnage n'attaque pas personnage et monstre n'attaque pas monstre
                '   test portée (faut il une méthode de calcul de distance?)
                '   jet d'attaque : 1d20 + attaquant.m_equipement(getCaractéristiqueAttaque())
                '                   (dans classe arme, il faut une méthode qui cherche la force ou la dex de l'entité pour l'additionner
                '                                       et une pour lancer le dé de dégat careespondant)
                '   test si jet d'attaque > defenseur.m_equipement(getArmure())
                '   degat: entitée.m_equipement(jetDeDégat())
                '           defenseur.setPV(getPV()-degat)
                '           test état du défenseur

prendre()
                ' test si un equipement présent sur la position du personnage
                ' personnage.m_Inventaire(ajoutEquipement(carte.getQuoiEstIci)
                ' supprimer l'objet de la map
                
deplacement()
                ' deplacement(): 2 choix:
                ' soit un par appel par case, comme si on déplacait l'entitée case par case, donc:
                '                                   test si destination est adjacent
                '                                   puis test si case destination est libre
                '                                   puis test si il reste des point de mouvement à l'entité
                '                                   puis déplacement
                '
                ' soit un appel pour tout le déplacement, plus en accord avec le sujet;
                '                                   test si case destination est libre
                '                                   test si distance inférieure à  vitesse/3
                '                                   déplacement

