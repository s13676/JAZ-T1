<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Wyliczanie rat kredytu</title>
</head>

<body>
<form action="/table/" method="POST">
  Wnioskowana kwota kredytu:<br>
  <input type="number" name="kwota" step="0.01" min="1000" required><br>
  Ilość rat:<br>
  <input type="number" name="raty" step="1" min="2" required><br>
  Oprocentowanie:<br>
  <input type="number" name="oprocentowanie" min="0.01" step="0.01" required><br>
  Opłata stała:<br>
  <input type="number" name="opstala" min="1" step="0.01" required><br>
  Rodzaj rat:<br>
  <select name="rodzaj" required>
  <option value="m">malejąca</option>
  <option value="s">stała</option>
</select>
<br><br>
<button type="submit">Wyślij</button>
</form>
</body>

</html>