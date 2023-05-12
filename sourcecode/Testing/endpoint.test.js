const supertest = require("supertest");
const server = require('./server');
const request = supertest(server)
test("it sends request to our search page",async done=>{
                const res = await request.get('/');
                expect(res.status).toBe(200);

                done();
},30000)

test("api call and receive data",async done=>{
    const res = await request.get("/search?product=arsenal&page=1&limit=3");
    expect(res.body.result).toBeDefined();
    done()
})
test("get all team ", async done=>{
    const res = await request.get("/teams");
    expect(res).toBeDefined();
    done();
})
test("get all kits ", async done=>{
    const res = await request.get("/kits");
    expect(res).toBeDefined();
    done();
})
test("add new team",async done=>{
    const res = await request.post("/addnewteam").send({team_name:"france"});
    expect(res).toBeDefined();
    done();
})
