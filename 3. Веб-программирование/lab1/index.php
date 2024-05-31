<?php
  session_start();
?>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>Lab 1</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body>

  <div id="maincontent">

    <!-- Header -->

    <div id="header">
      <h2>Кизилов Степан Александрович</h2>
      <h4>Группа P32312</h4>
      <h4>Вариант №3007</h4>
    </div>

    <!-- Sidebar -->

    <div id="sidebar">
      <div id="sidebar_content">
        <img src="img/areas.png">
      </div>  
    </div>

    <!-- Form -->

    <div id="place_for_form">

      <form action="script/script.php" method="get">

        <div class="form_wrapper">

          <!-- x coordintate -->
          <div id="form_left" class="form_block">
            <p>Введите X</p>
            <?php
              for ($i=-4; $i<=4 ; $i++) { 
                
                echo "<div class='radio_wrapper'>";
                echo "<input type='radio' name='x_value' id='x_value_$i' value=$i>";
                echo "<label for='x_value_$i'>$i</label>";
                echo "</div>";

              }
            ?>      
          </div>

          <!-- y coordinate -->
          <div id="form_center" class="form_block">
            <p>Введите Y</p>
            
            <input type='text' name='y_value' id='y_value' />
            <div id="y_message"></div>

          </div>

          <!-- radius -->
          <div id="form_right" class="form_block">
            <p>Введите R</p> 
            <?php
            
              for ($i=1; $i<=5 ; $i++) { 
                echo "<div class='checkbox_wrapper'>";
                echo "<input type='checkbox' name='r_value' id='r_value_$i' value=$i>";
                echo "<label for='r_value_$i'>$i</label>";
                echo "</div>";

              }
            
            ?>      
          </div>

          <div class="line_breaker"></div>

          <!-- Submit button -->

          <input type="submit" name="submit" value="Отправить" />

        </div>
        
      </form>

    </div>

    <!-- table -->

    <div id="table_div">
      <table>
        <thead>
          <tr>
            <th>X</th>
            <th>Y</th>
            <th>R</th>
            <th>Результат</th>
            <th>Время запроса</th>
            <th>Время работы</th>
          </tr>
        </thead>
        <tbody>
          <?php
            if (! isset($_SESSION['points'])){
              $_SESSION['points'] = array();
            } else {
              foreach ($_SESSION['points'] as $point) {
                echo '<tr>';
                foreach ($point as $value) {
                  echo '<td>'.$value.'</td>'; 
                }
                echo '</tr>';
              }
            }
          ?>
        </tbody>
      </table>
    </div>

  </div>
  
  <script src="js/script.js"></script>

</body>

</html>