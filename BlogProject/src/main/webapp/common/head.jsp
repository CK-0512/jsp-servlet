<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 테일윈드 불러오기 -->
<!-- 노말라이즈, 라이브러리 -->
<link href="https://cdn.jsdelivr.net/npm/daisyui@3.1.7/dist/full.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.tailwindcss.com"></script>
<!-- 제이쿼리 불러오기 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<!-- 폰트어썸 불러오기 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
<style>
	@font-face {
    font-family: 'GmarketSansMedium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

html > body, html > body .ProseMirror, html > body .toastui-editor-contents {
	font-family: 'GmarketSansMedium', sans-serif;
	text-underline-position:under;
}

html[data-theme="dark"] :is(.table-box-type-1 > table th, .table-box-type-1 > table td) {
	border-color: white;
}

.theme-toggle .fa-solid {
	display: none;
}

html[data-theme="dark"] .theme-toggle .fa-solid {
	display: inline;
}

html[data-theme="dark"] .theme-toggle .fa-regular {
	display: none;
}

body > section {
	width: 100%;
	display: flex;
	margin-top: 6rem;
	margin-left: 6px;
}

.table-box-type-1 > table {
	width: 100%;
}

.table-box-type-1 > table th, .table-box-type-1 > table td {
	border:1px solid black;
	padding: 10px;
	text-align: center;
}

html > body .ProseMirror, html > body .toastui-editor-contents {
	text-align: left;
	font-size: 1.25rem;
}

html > body .toast-ui-viewer .toastui-editor-contents {
	text-align: center;
}

.btn-text-link {
	color:black;
}

.btn-text-link:hover {
	color:red;
	text-decoration: underline;
}

.border-bottom-line {
	border-bottom : 1px solid #d1c7c7;
}

.item-tr > td > div {
	width : 17%;
	display : inline-flex;
	box-sizing: border-box;
	padding: 5px;
}

.item-tr > td > div > label {
	justify-content: flex-start;
	cursor: pointer;
}

.item-tr > td > div > label > input {
	margin-right:10px;
}

.radio-label input[type="radio"] {
    display: none;
}

.radio-label {
    display: inline-block;
    margin-right: 40px;
    cursor: pointer;
    position: relative;
}

.radio-label span::before {
    content: "";
    display: inline-block;
    width: 15px;
    height: 15px;
    border: 1px solid #000;
    margin-right: 5px;
    position: absolute;
    left: -20px;
    top: 2px;
}

.radio-label input[type="radio"]:checked + span::before {
    content: "\2713";
    text-align: center;
    font-size: 1.3rem;
    color: black;
}

.selected {
	font-size : 1.05rem;
	font-weight : bold;
}

input[type="file"] {
	width : 190px;
}

#auctionContent:hover {
	border-color: #eee9;
    box-shadow: 0 2px 10px 0 rgba(0,0,0,0.2);
}

#auctionContent {
	transition: box-shadow .2s ease;
}

.toastui-editor-contents p {
	text-align: left;
}
</style>
<title>${pageTitle }</title>