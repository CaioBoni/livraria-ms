using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Http;
using auth.Models;

namespace auth.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AuthController : ControllerBase
    {
        private readonly string VALID_TOKEN = "valid-token";
        
        [HttpGet("{token}")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        public ActionResult<bool> Get(string token)
        {
            Console.WriteLine($"{token}");
            if (token == VALID_TOKEN) return Ok(true);
            return Unauthorized(false);
        }

        [HttpPost]
        [ProducesResponseType(StatusCodes.Status200OK)]
        public ActionResult<string> Post([FromBody] UserModel user)
        {
            string VALID_USERNAME = "usuario@email.com";
            string VALID_PASSWORD = "senha";
            Console.WriteLine($"{user.User} - {user.Password}");
            if (user.User == VALID_USERNAME && user.Password == VALID_PASSWORD)
                return Ok(VALID_TOKEN);
            return Unauthorized("Usuário ou senha incorretos");
        }
    }
}
