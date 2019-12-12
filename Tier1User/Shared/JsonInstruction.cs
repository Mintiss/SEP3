using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Tier1User.Shared
{
    public class JsonInstruction
    {
        public String json;
        public String instruction;

        public JsonInstruction(String json, String instruction)
        {
            this.json = json;
            this.instruction = instruction;
        }

        public String getJson()
        {
            return json;
        }

        public String getInstruction()
        {
            return instruction;
        }

    }
}
