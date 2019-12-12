using Microsoft.EntityFrameworkCore.Migrations;

namespace SEP3Db.Migrations
{
    public partial class ItemLocation2 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<string>(
                name: "Location",
                table: "Items",
                nullable: true);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Location",
                table: "Items");
        }
    }
}
