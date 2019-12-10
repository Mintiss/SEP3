using Microsoft.EntityFrameworkCore.Migrations;

namespace SEP3Db.Migrations
{
    public partial class harrynkirilmagic : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Borrowed_Items_ItemId",
                table: "Borrowed");

            migrationBuilder.DropForeignKey(
                name: "FK_Borrowed_Users_Username",
                table: "Borrowed");

            migrationBuilder.DropIndex(
                name: "IX_Borrowed_ItemId",
                table: "Borrowed");

            migrationBuilder.DropIndex(
                name: "IX_Borrowed_Username",
                table: "Borrowed");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateIndex(
                name: "IX_Borrowed_ItemId",
                table: "Borrowed",
                column: "ItemId");

            migrationBuilder.CreateIndex(
                name: "IX_Borrowed_Username",
                table: "Borrowed",
                column: "Username");

            migrationBuilder.AddForeignKey(
                name: "FK_Borrowed_Items_ItemId",
                table: "Borrowed",
                column: "ItemId",
                principalTable: "Items",
                principalColumn: "ItemId",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Borrowed_Users_Username",
                table: "Borrowed",
                column: "Username",
                principalTable: "Users",
                principalColumn: "Username",
                onDelete: ReferentialAction.Restrict);
        }
    }
}
