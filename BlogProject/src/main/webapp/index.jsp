<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Blog</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: url('background.jpg') no-repeat center center fixed;
            background-size: cover;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .entry-button {
            background-color: #333;
            color: #fff;
            border: none;
            padding: 15px 30px;
            font-size: 24px;
            cursor: pointer;
            border-radius: 5px;
            text-decoration: none;
            box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.2);
            transition: background-color 0.3s ease-in-out;
        }
        .entry-button:hover {
            background-color: #d81b60;
        }
        .entry-button:active {
            transform: translateY(2px);
        }
        .entry-button:focus {
            outline: none;
        }
        .entry-button::before {
            content: "\f105";
            font-family: FontAwesome;
            margin-right: 10px;
        }
        h1 {
            color: #fff;
            font-size: 48px;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
        }
    </style>
</head>
<body>
    <h1>환영합니다!</h1>
    <a class="entry-button" href="/BlogInformation?cmd=blog_view">블로그 입장하기</a>
</body>
</html>