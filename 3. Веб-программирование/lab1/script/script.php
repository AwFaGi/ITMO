<?php
date_default_timezone_set("Europe/Moscow");

$start_time = microtime(true);

$req_time = date("d.m.Y H:i:s");

session_start();

function something_went_wrong(){
  echo "418. I'm a teapot";
  die();
}

function redirect_obratno(){
  header('Location: ../');
  die();
}

function fill_session($x, $y, $r, $result){
  global $start_time;
  global $req_time;
  if (! isset($_SESSION['points'])){
    $_SESSION['points'] = array();
  }
  array_push($_SESSION['points'], array(
    'x' => $x,
    'y' => $y,
    'r' => $r,
    'result' => $result,
    'req_time' => $req_time,
    'work_time' => number_format(microtime(true) - $start_time, 10)
    ) 
  );
  redirect_obratno();
}

if (! isset($_GET['x_value']) || ! isset($_GET['y_value']) || ! isset($_GET['r_value'])) {
  something_went_wrong();
}

if ((empty($_GET['x_value']) && $_GET['x_value'] !== '0') ||
    (empty($_GET['y_value']) && $_GET['y_value'] !== '0') ||
    (empty($_GET['r_value']) && $_GET['r_value'] !== '0')
    ){
  something_went_wrong();
}

$x_value = floatval($_GET['x_value']);
$y_value = floatval($_GET['y_value']);
$r_value = floatval($_GET['r_value']);

if ($r_value < 0) {
  something_went_wrong();
}


if ($x_value >= 0 && $y_value > 0){ # first quater
  fill_session($x_value, $y_value, $r_value, "MISS");
} elseif ($x_value <= 0 && $y_value >= 0) { #second quater
  if ($x_value*$x_value + $y_value*$y_value <= ($r_value*$r_value)/4){
    fill_session($x_value, $y_value, $r_value, "HIT");
  } else {
    fill_session($x_value, $y_value, $r_value, "MISS");
  }
} elseif ($x_value <= 0 && $y_value < 0) { #third quater
  if ($x_value + $y_value > -1*$r_value){
    fill_session($x_value, $y_value, $r_value, "HIT");
  } else {
    fill_session($x_value, $y_value, $r_value, "MISS");
  }
} elseif ($x_value >= 0 && $y_value <= 0) { #fourth quater
  if ($x_value <= $r_value && $y_value <= $r_value){
    fill_session($x_value, $y_value, $r_value, "HIT");
  } else {
    fill_session($x_value, $y_value, $r_value, "MISS");
  }
} else {
  something_went_wrong();
}

?>