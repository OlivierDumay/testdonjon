# TODO

## UML
- [x] : Classe dé
- [ ] : Compléter les classes avec les méthodes qui manquent
- [x] : Bouger Initiative
- [ ] : Classe equipement, mettre getArme(), getArmure()
- [x] : Faire les étiquettes d'affichage, ou les mettres? * pour les équipement?
- [x] : methode getEtiqeutte() dans en  tité
- [ ] : ou mettre m_ordre? tour ou partie?
- [x] : creer package partie
- [ ] : signature de methode d'interface dans classe interface?
- [ ] : mettre en static plein de truc, quoiestqui, quiestla, 
- [ ] : changer nom package objet par objetDeJeu
- [ ] : faire des classes pour l'affichage, pour les différentes partie de l'affichage
- [ ] : refaire les relation au propre
- [ ] : comment on recupere les bonus des classes/race
- [ ] : faire une mehose prendre et un s'équiper
- [ ] : 


## Code
- [ ] : creer le string de l'affichage, map + info
- [x] : methode calcul distance




demarrerPartie(carteParDefaut, 15)
   init carte
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

