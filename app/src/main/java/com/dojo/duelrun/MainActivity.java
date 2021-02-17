package com.dojo.duelrun;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    //botões do layout calculadora.
    private Button button01, button02, button03, button04, button05, button06, button07, button08, button09;
    private Button button0, button00, button000, restartbutton, Modeduelbutton, timebutton;
    private Button placarbutton, closebutton, clearbutton, dicebutton, coinbutton, morebutton01;
    private Button lessbutton01, morebutton02, lessbutton02;
    private TextView displaycenter, displaypl01, displaypl02;
    private int cntluck = 0, cntpl01 = 8000, cntpl02 = 8000, cntwin01 = 0, cntwin02 = 0, cnttime = 0;
    private int cntmodo = 3;
    private String placar;
    private Chronometer clocktime;
    private Timer timer;
    private TimerTask tasktime;
    private Boolean endgame = false, endtimegame = true;
    private ProgressBar barlife01, barlife02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        implementLayoutMainGame();
    }

    //Função usada para converter,guardar e juntar os numeros escolhidos no display do centro.
    private void appendValue(String value) {
        if (Integer.parseInt(displaycenter.getText().toString()) == 0) {
            displaycenter.setText(value);
        } else if (displaycenter.length() < 5) {
            displaycenter.append(value);
        }
    }

    //Função usada para remover caracter individual do display do centro.
    private void removeValue() {
        if (displaycenter.getText().toString().length() > 1) {
            displaycenter.setText(
                    displaycenter.getText().toString().substring(
                            0, displaycenter.getText().toString().length() - 1));
        } else {
            displaycenter.setText("0");
        }
    }

    //Função feita para guardar o resultado na memória.
    private void imprimirplacar() {
        placar = (String.valueOf(cntwin01) + ":" + String.valueOf(cntwin02));
    }

    private void restartduel() {
        switch (cntmodo) {
            case 1:
                cntpl01 = 2000;
                cntpl02 = 2000;
                barlife01.setProgress(cntpl01 * 4);
                barlife02.setProgress(cntpl02 * 4);
                displaycenter.setText("0");
                displaypl01.setText("2000");
                displaypl02.setText("2000");
                displaypl01.setTextColor(Color.WHITE);
                displaypl02.setTextColor(Color.WHITE);
                endgame = false;
                cntmodo = 1;
                break;
            case 2:
                cntpl01 = 4000;
                cntpl02 = 4000;
                barlife01.setProgress(cntpl01 * 2);
                barlife02.setProgress(cntpl02 * 2);
                displaycenter.setText("0");
                displaypl01.setText("4000");
                displaypl02.setText("4000");
                displaypl01.setTextColor(Color.WHITE);
                displaypl02.setTextColor(Color.WHITE);
                endgame = false;
                cntmodo = 2;
                break;
            case 3:
                cntpl01 = 8000;
                cntpl02 = 8000;
                barlife01.setProgress(cntpl01);
                barlife02.setProgress(cntpl02);
                displaycenter.setText("0");
                displaypl01.setText("8000");
                displaypl02.setText("8000");
                displaypl01.setTextColor(Color.WHITE);
                displaypl02.setTextColor(Color.WHITE);
                endgame = false;
                cntmodo = 3;
                break;
            case 4:
                cntpl01 = 16000;
                cntpl02 = 16000;
                barlife01.setProgress(cntpl01 / 2);
                barlife02.setProgress(cntpl02 / 2);
                displaycenter.setText("0");
                displaypl01.setText("16000");
                displaypl02.setText("16000");
                displaypl01.setTextColor(Color.CYAN);
                displaypl02.setTextColor(Color.CYAN);
                endgame = false;
                cntmodo = 4;
                break;
            default:
                AlertDialog helloAlert1 = new AlertDialog.Builder(MainActivity.this).create();
                helloAlert1.setTitle("Try Again");
                helloAlert1.setMessage("Sorry , Bad command");
                helloAlert1.setButton(AlertDialog.BUTTON_NEUTRAL, "Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        cntmodo = 0;
                    }
                });
        }
    }

    //Função central para criar a base da calculadora TCG.
    @SuppressLint("NewApi")
    private void implementLayoutMainGame() {
        final String[] str = new String[5];
        setContentView(R.layout.maingame);
        //vtcnt1 = new int[5];
        //vtcnt2 = new int[5];
        //i1=0;
        //i2=0;
        str[0] = "Close";
        str[1] = "Battle Pack : 2000LP";
        str[2] = "Sneak Peek : 4000LP";
        str[3] = "Standard Duel : 8000LP";
        str[4] = "Tag Duel : 16000LP";
        cntmodo = 3;
        //song = MediaPlayer.create(MainActivity.this,R.raw.clickbutton);
        //songendgame = MediaPlayer.create(MainActivity.this,R.raw.explosion);
        //songlifegame = MediaPlayer.create(MainActivity.this, R.raw.lifepointsong);
        //songresstart = MediaPlayer.create(MainActivity.this, R.raw.resertgame);
        imprimirplacar();
        barlife01 = (ProgressBar) findViewById(R.id.lifebar1);
        barlife02 = (ProgressBar) findViewById(R.id.lifebar2);
        barlife01.setProgress(cntpl01);
        barlife02.setProgress(cntpl02);
        displaypl01 = (TextView) findViewById(R.id.textViewdisplay01);
        displaypl02 = (TextView) findViewById(R.id.textViewdisplay02);
        displaycenter = (TextView) findViewById(R.id.textView1);
        clocktime = (Chronometer) findViewById(R.id.clocktime);
        clocktime.setText("00:00");

        button01 = (Button) findViewById(R.id.button01);
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendValue("1");
            }
        });
        button02 = (Button) findViewById(R.id.button02);
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendValue("2");
            }
        });
        button03 = (Button) findViewById(R.id.button03);
        button03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendValue("3");
            }
        });
        button04 = (Button) findViewById(R.id.button04);
        button04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendValue("4");
            }
        });
        button05 = (Button) findViewById(R.id.button05);
        button05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendValue("5");
            }
        });
        button06 = (Button) findViewById(R.id.button06);
        button06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendValue("6");
            }
        });
        button07 = (Button) findViewById(R.id.button07);
        button07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendValue("7");
            }
        });
        button08 = (Button) findViewById(R.id.button08);
        button08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendValue("8");
            }
        });
        button09 = (Button) findViewById(R.id.button09);
        button09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendValue("9");
            }
        });
        button0 = (Button) findViewById(R.id.button0);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendValue("0");
            }
        });
        button00 = (Button) findViewById(R.id.button00);
        button00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendValue("0");
                appendValue("0");
            }
        });
        button000 = (Button) findViewById(R.id.button000);
        button000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendValue("0");
                appendValue("0");
                appendValue("0");
            }
        });
        closebutton = (Button) findViewById(R.id.buttonclose);
        closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        timebutton = (Button) findViewById(R.id.buttontime);
        timebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (cnttime) {
                    case 0:
                        clocktime.setBase(SystemClock.elapsedRealtime());
                        clocktime.start();
                        cnttime++;
                        timer = new Timer();
                        tasktime = new TimerTask() {
                            @Override
                            public void run() {
                                timebutton.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (endtimegame == true) {
                                            imprimirplacar();
                                            AlertDialog helloAlert = new AlertDialog.Builder(MainActivity.this).create();
                                            helloAlert.setTitle("TIME");
                                            helloAlert.setMessage("Finish the Duel \n" + placar);
                                            helloAlert.setButton(AlertDialog.BUTTON_NEUTRAL, "Close", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface arg0, int arg1) {
                                                }
                                            });
                                            helloAlert.show();
                                            clocktime.stop();
                                            endtimegame = false;
                                        } else if (endtimegame == false) {
                                            tasktime.cancel();
                                        }
                                    }
                                });
                            }
                        };
                        timer.scheduleAtFixedRate(tasktime, 1800000, 1000); //30 minutos = 1800000
                        AlertDialog helloAlert = new AlertDialog.Builder(MainActivity.this).create();
                        helloAlert.setTitle("TIME : 30:00");
                        helloAlert.setMessage("It's time to duel!");
                        helloAlert.setButton(AlertDialog.BUTTON_NEUTRAL, "Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                            }
                        });
                        helloAlert.show();
                        break;
                    case 1:
                        AlertDialog helloAlert1 = new AlertDialog.Builder(MainActivity.this).create();
                        helloAlert1.setTitle("TIME");
                        helloAlert1.setMessage(clocktime.getText().toString());
                        helloAlert1.setButton(AlertDialog.BUTTON_NEUTRAL, "Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                            }
                        });
                        helloAlert1.setButton(AlertDialog.BUTTON_NEGATIVE, "Again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                clocktime.stop();
                                clocktime.setText("00:00");
                                cnttime--;
                                endtimegame = true;
                            }
                        });
                        helloAlert1.show();
                        break;
                    default:
                        cnttime = 0;
                        break;
                }
            }
        });
        Modeduelbutton = (Button) findViewById(R.id.buttonmode);
        Modeduelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder hello = new AlertDialog.Builder(MainActivity.this);
                hello.setTitle("Modo de Duelo!");
                hello.setSingleChoiceItems(str, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                dialog.dismiss();
                                break;
                            case 1:
                                cntmodo = 1;
                                cntpl01 = 2000;
                                cntpl02 = 2000;
                                cntwin01 = 0;
                                cntwin02 = 0;
                                barlife01.setProgress(cntpl01 * 4);
                                barlife02.setProgress(cntpl02 * 4);
                                displaycenter.setText("0");
                                displaypl01.setText("2000");
                                displaypl02.setText("2000");
                                displaycenter.setTextColor(Color.WHITE);
                                displaypl01.setTextColor(Color.WHITE);
                                displaypl02.setTextColor(Color.WHITE);
                                clocktime.stop();
                                cnttime--;
                                endtimegame = true;
                                clocktime.setText("0:00");
                                endgame = false;
                                dialog.dismiss();
                                break;
                            case 2:

                                cntmodo = 2;
                                cntpl01 = 4000;
                                cntpl02 = 4000;
                                cntwin01 = 0;
                                cntwin02 = 0;
                                barlife01.setProgress(cntpl01 * 2);
                                barlife02.setProgress(cntpl02 * 2);
                                displaycenter.setText("0");
                                displaypl01.setText("4000");
                                displaypl02.setText("4000");
                                displaycenter.setTextColor(Color.WHITE);
                                displaypl01.setTextColor(Color.WHITE);
                                displaypl02.setTextColor(Color.WHITE);
                                clocktime.stop();
                                cnttime--;
                                endtimegame = true;
                                clocktime.setText("0:00");
                                endgame = false;
                                dialog.dismiss();
                                break;
                            case 3:

                                cntmodo = 3;
                                cntpl01 = 8000;
                                cntpl02 = 8000;
                                cntwin01 = 0;
                                cntwin02 = 0;
                                barlife01.setProgress(cntpl01);
                                barlife02.setProgress(cntpl02);
                                displaycenter.setText("0");
                                displaypl01.setText("8000");
                                displaypl02.setText("8000");
                                displaycenter.setTextColor(Color.WHITE);
                                displaypl01.setTextColor(Color.WHITE);
                                displaypl02.setTextColor(Color.WHITE);
                                clocktime.stop();
                                cnttime--;
                                endtimegame = true;
                                clocktime.setText("0:00");
                                endgame = false;
                                dialog.dismiss();
                                break;
                            case 4:

                                cntmodo = 4;
                                cntpl01 = 16000;
                                cntpl02 = 16000;
                                cntwin01 = 0;
                                cntwin02 = 0;
                                barlife01.setProgress(cntpl01 / 2);
                                barlife02.setProgress(cntpl02 / 2);
                                displaycenter.setText("0");
                                displaypl01.setText("16000");
                                displaypl02.setText("16000");
                                displaycenter.setTextColor(Color.WHITE);
                                displaypl01.setTextColor(Color.CYAN);
                                displaypl02.setTextColor(Color.CYAN);
                                clocktime.stop();
                                cnttime--;
                                endtimegame = true;
                                clocktime.setText("0:00");
                                endgame = false;
                                dialog.dismiss();
                                break;
                            default:
                                cntmodo = 3;
                                dialog.dismiss();
                                break;
                        }
                    }
                });
                hello.show();
            }
        });
        placarbutton = (Button) findViewById(R.id.buttonplacar);
        placarbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog helloAlert = new AlertDialog.Builder(MainActivity.this).create();
                imprimirplacar();
                helloAlert.setTitle("SCOREBOARD");
                helloAlert.setMessage(placar);
                helloAlert.setButton(AlertDialog.BUTTON_NEUTRAL, "Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
                helloAlert.show();
            }
        });
        restartbutton = (Button) findViewById(R.id.buttonresetduel);
        restartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog helloAlert = new AlertDialog.Builder(MainActivity.this).create();
                helloAlert.setTitle("DUEL!");
                helloAlert.setMessage("Star a new Duel?");
                helloAlert.setButton(AlertDialog.BUTTON_NEUTRAL, "No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
                helloAlert.setButton(AlertDialog.BUTTON_NEGATIVE, "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        if (cntpl01 > cntpl02) {
                            cntwin01++;
                            restartduel();
                        } else if (cntpl02 > cntpl01) {
                            cntwin02++;
                            restartduel();
                        } else {
                            restartduel();
                        }
                    }
                });
                helloAlert.show();
            }
        });
        clearbutton = (Button) findViewById(R.id.buttonclear);
        clearbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeValue();
            }
        });
        morebutton01 = (Button) findViewById(R.id.buttonmore01);
        morebutton01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (endgame == false) {
                    cntpl01 = cntpl01 + Integer.parseInt(displaycenter.getText().toString());
    				/*
    				vtcnt1[i1]=cntpl01;
    				if(i1>=0&&i1<=4){
    					i1++;
    				}else{
    					i1=0;
    				}*/
                    switch (cntmodo) {
                        case 1:
                            barlife01.setProgress(cntpl01 * 4);
                            break;
                        case 2:
                            barlife01.setProgress(cntpl01 * 2);
                            break;
                        case 3:
                            barlife01.setProgress(cntpl01);
                            break;
                        case 4:
                            barlife01.setProgress(cntpl01 / 2);
                            break;
                        default:
                            break;
                    }
                    //barlife01.setProgress(cntpl01);
                    displaypl01.setText(String.valueOf(cntpl01));
                    displaycenter.setText("0");
                    if ((cntpl01 <= 3000) && (cntpl01 >= 1001)) {
                        displaypl01.setTextColor(Color.YELLOW);
                    } else if ((cntpl01 <= 8000) && (cntpl01 >= 3001)) {
                        displaypl01.setTextColor(Color.WHITE);
                    } else if (cntpl01 >= 8001) {
                        displaypl01.setTextColor(Color.CYAN);
                    }
                }
            }
        });
        lessbutton01 = (Button) findViewById(R.id.buttonless01);
        lessbutton01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cntpl01 = cntpl01 - Integer.parseInt(displaycenter.getText().toString());
    			/*
				vtcnt1[i1]=cntpl01;
				if(i1>=0&&i1<=4){
					i1++;
				}else{
					i1=0;
				}*/
                switch (cntmodo) {
                    case 1:
                        barlife01.setProgress(cntpl01 * 4);
                        break;
                    case 2:
                        barlife01.setProgress(cntpl01 * 2);
                        break;
                    case 3:
                        barlife01.setProgress(cntpl01);
                        break;
                    case 4:
                        barlife01.setProgress(cntpl01 / 2);
                        break;
                    default:
                        break;
                }
                //barlife01.setProgress(cntpl01);
                if (endgame == false) {
                    if (cntpl01 < 0) {
                        cntpl01 = 0;
                    }
                    displaypl01.setText(String.valueOf(cntpl01));
                    displaycenter.setText("0");
                    if (cntpl01 <= 0 && endgame == false) {
                        AlertDialog helloAlert = new AlertDialog.Builder(MainActivity.this).create();
                        helloAlert.setTitle("WIN!");
                        helloAlert.setMessage("Player2");
                        helloAlert.setButton(AlertDialog.BUTTON_NEUTRAL, "Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                            }
                        });
                        helloAlert.setButton(AlertDialog.BUTTON_NEGATIVE, "Restart", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                restartduel();
                            }
                        });
                        helloAlert.show();
                        if (endgame == false) {
                            cntwin02++;
                            endgame = true;
                        }
                        imprimirplacar();
                    }
                    if ((cntpl01 <= 8000) && (cntpl01 >= 3001)) {
                        displaypl01.setTextColor(Color.WHITE);
                    } else if ((cntpl01 <= 3000) && (cntpl01 >= 1001)) {
                        displaypl01.setTextColor(Color.YELLOW);
                    } else if (cntpl01 < 1001) {
                        displaypl01.setTextColor(Color.RED);
                    }
                }
            }
        });
        morebutton02 = (Button) findViewById(R.id.buttonmore02);
        morebutton02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (endgame == false) {
                    cntpl02 = cntpl02 + Integer.parseInt(displaycenter.getText().toString());
    				/*
    				vtcnt2[i2]=cntpl02;
    				if(i2>=0&&i2<=4){
    					i2++;
    				}else{
    					i2=0;
    				}*/
                    switch (cntmodo) {
                        case 1:
                            barlife02.setProgress(cntpl02 * 4);
                            break;
                        case 2:
                            barlife02.setProgress(cntpl02 * 2);
                            break;
                        case 3:
                            barlife02.setProgress(cntpl02);
                            break;
                        case 4:
                            barlife02.setProgress(cntpl02 / 2);
                            break;
                        default:
                            break;
                    }
                    //barlife02.setProgress(cntpl02);
                    displaypl02.setText(String.valueOf(cntpl02));
                    displaycenter.setText("0");
                    if ((cntpl02 <= 3000) && (cntpl02 >= 1001)) {
                        displaypl02.setTextColor(Color.YELLOW);
                    } else if ((cntpl02 <= 8000) && (cntpl02 >= 3001)) {
                        displaypl02.setTextColor(Color.WHITE);
                    } else if (cntpl02 >= 8001) {
                        displaypl02.setTextColor(Color.CYAN);
                    }
                }
            }
        });
        lessbutton02 = (Button) findViewById(R.id.buttonless02);
        lessbutton02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cntpl02 = cntpl02 - Integer.parseInt(displaycenter.getText().toString());

                switch (cntmodo) {
                    case 1:
                        barlife02.setProgress(cntpl02 * 4);
                        break;
                    case 2:
                        barlife02.setProgress(cntpl02 * 2);
                        break;
                    case 3:
                        barlife02.setProgress(cntpl02);
                        break;
                    case 4:
                        barlife02.setProgress(cntpl02 / 2);
                        break;
                    default:
                        break;
                }
                if (endgame == false) {
                    if (cntpl02 < 0) {
                        cntpl02 = 0;
                    }
                    displaypl02.setText(String.valueOf(cntpl02));
                    displaycenter.setText("0");
                    if (cntpl02 <= 0 && endgame == false) {
                        AlertDialog helloAlert = new AlertDialog.Builder(MainActivity.this).create();
                        helloAlert.setTitle("WIN!");
                        helloAlert.setMessage("Player1");
                        helloAlert.setButton(AlertDialog.BUTTON_NEUTRAL, "Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                            }
                        });
                        helloAlert.setButton(AlertDialog.BUTTON_NEGATIVE, "Restart", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                restartduel();
                            }
                        });
                        helloAlert.show();
                        if (endgame == false) {
                            cntwin01++;
                            endgame = true;
                        }
                        imprimirplacar();
                    }
                    if ((cntpl02 <= 8000) && (cntpl02 >= 3001)) {
                        displaypl02.setTextColor(Color.WHITE);
                    } else if ((cntpl02 <= 3000) && (cntpl02 >= 1001)) {
                        displaypl02.setTextColor(Color.YELLOW);
                    } else if (cntpl02 < 1001) {
                        displaypl02.setTextColor(Color.RED);
                    }
                }
            }
        });
        dicebutton = (Button) findViewById(R.id.buttondice);
        dicebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cntluck = (int) (Math.random() * 6) + 1;
                AlertDialog helloAlert = new AlertDialog.Builder(MainActivity.this).create();
                helloAlert.setTitle("DICE");
                helloAlert.setMessage(String.valueOf(cntluck));
                helloAlert.setButton(AlertDialog.BUTTON_POSITIVE, "Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
                helloAlert.show();
            }
        });
        coinbutton = (Button) findViewById(R.id.buttoncoin);
        coinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cntluck = (int) (Math.random() * 6) + 1;
                if (cntluck == 1 || cntluck == 3 || cntluck == 5) {
                    AlertDialog helloAlert = new AlertDialog.Builder(MainActivity.this).create();
                    helloAlert.setTitle("COIN");
                    helloAlert.setMessage("Heads");
                    helloAlert.setButton(AlertDialog.BUTTON_NEUTRAL, "Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });
                    helloAlert.show();
                } else {
                    AlertDialog helloAlert = new AlertDialog.Builder(MainActivity.this).create();
                    helloAlert.setTitle("COIN");
                    helloAlert.setMessage("Tails");
                    helloAlert.setButton(AlertDialog.BUTTON_NEUTRAL, "Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });
                    helloAlert.show();
                }
            }
        });
    }
}