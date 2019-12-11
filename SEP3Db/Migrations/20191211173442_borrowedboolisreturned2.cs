using Microsoft.EntityFrameworkCore.Migrations;

namespace SEP3Db.Migrations
{
    public partial class borrowedboolisreturned2 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<bool>(
                name: "IsReturned",
                table: "Borrowed",
                nullable: false,
                defaultValue: false);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "IsReturned",
                table: "Borrowed");
        }
    }
}
