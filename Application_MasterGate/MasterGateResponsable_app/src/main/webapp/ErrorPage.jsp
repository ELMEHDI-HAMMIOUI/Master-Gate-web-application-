<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }
        .error-container {
            text-align: center;
            background: white;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .error-container svg {
            width: 100px;
            height: 100px;
        }
        .error-container h1 {
            font-size: 24px;
            margin: 20px 0;
        }
        .error-container p {
            font-size: 16px;
            margin: 10px 0;
        }
        .main-btn  {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            font-size: 16px;
            color: white;
            background-color: #007BFF;
            text-decoration: none;
            border-radius: 4px;
        }
        .main-btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="red" width="100px" height="100px">
            <path d="M0 0h24v24H0z" fill="none"/>
            <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm0 18c-4.41 0-8-3.59-8-8s3.59-8 8-8 8 3.59 8 8-3.59 8-8 8zm1-11h-2v6h2v-6zm0 8h-2v2h2v-2z"/>
        </svg>
        <h1>Something Went Wrong</h1>
        <p>We are sorry, but something went wrong. Please try again later.</p>
        <button onclick="history.back()" class="main-btn">Back</button>

        
    </div>
</body>
</html>
