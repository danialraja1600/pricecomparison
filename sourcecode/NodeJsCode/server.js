//importing dependencies
const express = require("express");
const app = express();
const bodyParser = require("body-parser");
var exphbs = require("express-handlebars");
const mysql = require("mysql");
//database configuration
var con = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "",
  database: "test"
});
con.connect(function(err) {
  if (err) throw err;
  console.log("Connected!");  
});
//requiring models

//middlewares
app.use(bodyParser());
app.engine("handlebars", exphbs());
app.set("view engine", "handlebars");

//api endpoints
app.get("/", (req, res) => {
  res.render("index", { layout: false });
});
//pagination for searching products and sedning json response
//rest endpoint for product details paginated api too
app.get("/search", (req, res) => {
  let { page, limit } = req.query;
  const { product } = req.query;

  !page && (page = 1);
  !limit && (limit = 6);
  //implementing logic for pagination
  const startIndex = (page - 1) * limit;
  const endIndex = page * limit;
  //fetching data from database using sql query 
  con.query(
    `SELECT * FROM products WHERE product_name LIKE '%${product}%' OR product_description LIKE '%${product}%'`,
    (err, rows) => {
      const results = {};
      if (endIndex < rows.length) {
        results.next = {
          page: +page + 1,
          limit: limit
        };
      }

      if (startIndex > 0) {
        results.prev = {
          page: +page - 1,
          limit: limit
        };
      }

      results.result = rows.slice(startIndex, endIndex);
      res.json(results); // sending  response in json format with all product details 
    
    }
  );
});
//edpoint giving all teams in project
app.get("/teams",(req,res)=>{
 conn.query(`select * from teams`,(err,rows)=>{
    res.json(rows);
  })
})
//end point giving all kits in project
app.get("/kits",(req,res)=>{
  conn.query(`select * from kits`,(err,rows)=>{
    if(err) throw err;
    res.json(rows);
  })
})
//endpoint for inserting new team
app.post("/addnewteam",(req,res)=>{
  const {team_name} = req.body;
  const sql = `INSERT INTO teams(team_name) values('${team_name}')`;
  conn.query(sql,(err,rows)=>{
    if(err) throw err;
    return rows;
  })
})

//server listening on port 5000 or port assigned by the process
module.exports = 
app.listen(process.env.PORT || 5000, () => {
  console.log(`server running on port 5000`);
});
