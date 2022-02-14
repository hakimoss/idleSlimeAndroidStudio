package com.hakim.idleslim;

import static com.hakim.idleslim.GameView.screenRatioX;
import static com.hakim.idleslim.GameView.screenRatioY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;

public class Hero extends Personnages {

//    private Image imgHero;
//    private ImageIcon icoHero;

    Bitmap imgHero1, imgHero2, imgHero3, imgHero4;

    int x, y;

    int compteurMarche = 0;

    int width, height;

    private String playerName;
    private long lastUpdate;

    private int health;
    private int healthLvl;
    private int healthRegen;
    private int dmg;
    private int dmgAvantEquip;
    private int gold;
    private int dmgPrice;
    private int stageMax;
    private String email;
    private String selectedHero;
    private boolean slimHerbe;
    private boolean slimFeu;
    private boolean slimEau;

    private boolean libre;
    private boolean enCombat;

    private int compteurHealthRegen;

    //public String[] itemInInventory = new String[10];

//    public Item[] itemInInventory = new Item[10];

//    public Item[] equipedItem = new Item[2];


    public Hero(int x, int y, Resources res) {
        super(x, y, 60, 49);

//        this.icoHero = new ImageIcon(getClass().getResource("/images/hero1Droite.png"));
//        this.imgHero = this.icoHero.getImage();

        imgHero1 = BitmapFactory.decodeResource(res, R.drawable.hero_herbe_1droite);
        imgHero2 = BitmapFactory.decodeResource(res, R.drawable.hero_herbe_2droite);
        imgHero3 = BitmapFactory.decodeResource(res, R.drawable.hero_herbe_3droite);
        imgHero4 = BitmapFactory.decodeResource(res, R.drawable.hero_herbe_4droite);

        width = imgHero1.getWidth();
        height = imgHero1.getHeight();

        width *= 1.5;
        height *= 2.5;

        width *= (int) screenRatioX;
        height *= screenRatioY;

        System.out.println("width : "+width);
        Log.d("DEBUG", "width : "+width);

        imgHero1 = Bitmap.createScaledBitmap(imgHero1, width,(int) height, false);
        imgHero2 = Bitmap.createScaledBitmap(imgHero2, width,(int) height, false);
        imgHero3 = Bitmap.createScaledBitmap(imgHero3, width,(int) height, false);
        imgHero4 = Bitmap.createScaledBitmap(imgHero4, width,(int) height, false);

        this.y = y / 2;
        this.x = (int) (64 * screenRatioX);

        this.libre = true;
        //RuneDmg runeDamage1 = new RuneDmg("RDW", "white", 3);

        this.healthLvl = 1;
        this.health = 90;
        this.healthRegen = 1;
        this.dmg = 2;
        this.dmgAvantEquip = 2;
        this.enCombat = false;
        this.gold = 0;
        this.dmgPrice = this.dmg*10;
        this.stageMax = 0;
        this.email = null;
        this.playerName = null;
        this.selectedHero = null;
        this.slimHerbe = false;
        this.slimFeu = false;
        this.slimEau = false;
//        this.itemInInventory[0] = new Item();
//        this.itemInInventory[1] = new Item();
//        this.itemInInventory[2] = new Item();
//        this.itemInInventory[3] = new Item();
//        this.itemInInventory[4] = new Item();
//        this.itemInInventory[5] = new Item();
//        this.itemInInventory[6] = new Item();
//        this.itemInInventory[7] = new Item();
//        this.itemInInventory[8] = new Item();
//        this.itemInInventory[9] = new Item();
//
//
//        this.equipedItem[0] = new RuneDmg();
//        this.equipedItem[1] = new RuneDmg();

    }

    //   GETTERS   //
//    public Image getImgHero() {return imgHero;}

    public boolean isLibre() {return libre;}

    public int getHealth() {return health;}

    public int getDmg() {return dmg;}

    public int getDmgAvantEquip() {return dmgAvantEquip;}

    public boolean isEnCombat() {return enCombat;}

    public int getGold() {return gold;}

    public int getDmgPrice() {return dmgPrice;}

    public int getHealthLvl() {return healthLvl;}

    public int getStageMax() {return stageMax;}

    public String getEmail() {return email;}

    public String getPlayerName() {return playerName;}

    public long getLastUpdate() {return lastUpdate;}

    public String getSelectedHero() {return selectedHero;}

    public boolean isSlimHerbe() {return slimHerbe;}

    public boolean isSlimFeu() {return slimFeu;}

    public boolean isSlimEau() {return slimEau;}

    public int getHealthRegen() {return healthRegen;}

    //   SETTERS   //
    public void setLibre(boolean libre) {this.libre = libre;}

    public void setHealth(int health) {this.health = health;}

    public void setDmg(int dmg) {this.dmg = dmg;}

    public void setDmgAvantEquip(int dmgAvantEquip) {this.dmgAvantEquip = dmgAvantEquip;}

    public void setEnCombat(boolean enCombat) {this.enCombat = enCombat;}

    public void setGold(int gold) {this.gold = gold;}

    public void setDmgPrice(int dmgPrice) {this.dmgPrice = dmgPrice;}

    public void setHealthLvl(int healthLvl) {this.healthLvl = healthLvl;}

    public void setStageMax(int stageMax) {this.stageMax = stageMax;}

    public void setEmail(String email) {this.email = email;}

    public void setPlayerName(String playerName) {this.playerName = playerName;}

    public void setLastUpdate(long lastUpdate) {this.lastUpdate = lastUpdate;}

    public void setSelectedHero(String selectedHero) {this.selectedHero = selectedHero;}

    public void setSlimHerbe(boolean slimHerbe) {this.slimHerbe = slimHerbe;}

    public void setSlimFeu(boolean slimFeu) {this.slimFeu = slimFeu;}

    public void setSlimEau(boolean slimEau) {this.slimEau = slimEau;}

    public void setHealthRegen(int healthRegen) {this.healthRegen = healthRegen;}

    //   METHODS   //

    Bitmap getHero() {
        if(compteurMarche < 2) {
            compteurMarche++;
            return imgHero1;
        } else if(compteurMarche >= 2 && compteurMarche < 4) {
            compteurMarche++;
            return imgHero2;
        } else if(compteurMarche >= 4 && compteurMarche < 6) {
            compteurMarche++;
            return imgHero3;
        } else if(compteurMarche >= 6 && compteurMarche < 8) {
            compteurMarche++;
            if(compteurMarche == 7) {
                compteurMarche = 0;
            }
            return imgHero4;
        } else {
            return null;
        }


    }

    Rect getCollisionShape() {

        System.out.println(x+width-10);
        return new Rect(x, y, x+width-10, y+height);
    }

//    public void contact(Personnages personnage) {
//        if(personnage.isVivant() == true) {
//            if(super.contactAvant(personnage) == true || super.contactArriere(personnage) ==  true ) {
//                Main.scene.setDx(0);
//                this.libre = false;
//                this.setMarche(false);
//
//
//                if(this.isEnCombat() == false) {
//                    Combat combat = new Combat();
//                    combat.combatEntrePersonnages(this, personnage);
//                    this.setEnCombat(true);
//                }
//
//
//            }
//        }
//
//    }

//    public Image marche(String nom, int frequence) {
//
//        String str;
//        ImageIcon ico;
//        Image img;
//
//
//
//        if(this.marche == false) {
//            if(this.isVersDroite() == true) {
//                str = "/images/"+nom+"1Droite.png";
//            } else {
//                str = "images/"+nom+"1Gauche.png";
//            }
//        } else {
//            ActionListener listener = new TimeListener();
//            Timer timer = new Timer(1000, listener);
//            timer.start();
//            this.compteurMarche++;
//
//
//
//            if(this.compteurMarche >=4) {
//                this.compteurMarche = 0;
//            }
//            if(this.health < 90 + (healthLvl * 10)) {
//                this.compteurHealthRegen++;
//                if(this.compteurHealthRegen >=20) {
//
//                    this.health = this.health + this.healthRegen;
//                    this.compteurHealthRegen = 0;
//                    if(this.health > 90 + (healthLvl * 10)) {
//                        this.health = 90 + (healthLvl * 10);
//                    }
//                }
//            }
//
//            if(this.isVersDroite() == true) {
//                if(this.compteurMarche == 1) {
//                    str = "/images/"+nom+"2Droite.png";
//                } else if(this.compteurMarche == 2) {
//                    str = "/images/"+nom+"3Droite.png";
//                } else if(this.compteurMarche == 3) {
//                    str = "/images/"+nom+"4Droite.png";
//                } else {
//                    str = "/images/"+nom+"1Droite.png";
//                }
//            } else {
//                if(this.compteurMarche == 1) {
//                    str = "/images/"+nom+"2Gauche.png";
//                } else if(this.compteurMarche == 2) {
//                    str = "/images/"+nom+"3Gauche.png";
//                } else if(this.compteurMarche == 3) {
//                    str = "/images/"+nom+"4Gauche.png";
//                } else {
//                    str = "/images/"+nom+"1Gauche.png";
//                }
//            }
//
//        }
//        ico=new ImageIcon(getClass().getResource(str));
//        img=ico.getImage();
//        return img;
//
//    }



}