using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using credit_card.Models;

namespace credit_card.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class TransactionsController : ControllerBase
    {
        private const string VALID_ID = "transaction-id";

        [HttpGet("{id}")]
        public ActionResult<string> Get([FromQuery] string id)
        {
            if (id != VALID_ID)
                return NotFound("Transação não encontrada");
            return Ok();
        }

        [HttpPost]
        public ActionResult Post([FromBody] TransactionModel newTransaction)
        {
            Console.WriteLine($"{newTransaction.CreditCardNumber} - ${newTransaction.InvoiceValue}");
            return Created(nameof(Get), new { response = "Pedido de transação efetuada com sucesso" });
        }

        [HttpDelete("{id}")]
        public ActionResult Cancel([FromQuery] string id)
        {
            if (id != VALID_ID)
                return NotFound("Transação não encontrada");
            return NoContent();
        }
    }
}
