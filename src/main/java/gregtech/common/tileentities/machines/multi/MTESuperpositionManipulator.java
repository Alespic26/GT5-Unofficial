package gregtech.common.tileentities.machines.multi;
import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.ISurvivalBuildEnvironment;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import gregtech.api.GregTechAPI;
import gregtech.api.enums.Materials;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.MTEExtendedPowerMultiBlockBase;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GTUtility;
import gregtech.api.util.MultiblockTooltipBuilder;
import gregtech.common.blocks.BlockCasings10;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.onElementPass;
import static gregtech.api.enums.HatchElement.*;
import static gregtech.api.enums.Textures.BlockIcons.*;
import static gregtech.api.util.GTStructureUtility.buildHatchAdder;
import static gregtech.api.util.GTStructureUtility.ofFrame;
public class MTESuperpositionManipulator extends MTEExtendedPowerMultiBlockBase<MTESuperpositionManipulator> implements ISurvivalConstructable  {

    private static final String STRUCTURE_PIECE_MAIN = "main";
    private static final IStructureDefinition<MTESuperpositionManipulator> STRUCTURE_DEFINITION = StructureDefinition
        .<MTESuperpositionManipulator>builder()
        .addShape(
            STRUCTURE_PIECE_MAIN,
            // spotless:off
            new String[][]{{
                "           ",
                "           ",
                "           ",
                "           ",
                "    AAA    ",
                "    A~A    ",
                "    AAA    ",
                "           ",
                "           ",
                "           ",
                "           "
            },{
                "           ",
                "           ",
                "           ",
                "           ",
                "           ",
                "     C     ",
                "           ",
                "           ",
                "           ",
                "           ",
                "           "
            },{
                "           ",
                "           ",
                "           ",
                "           ",
                "           ",
                "     D     ",
                "           ",
                "           ",
                "           ",
                "           ",
                "           "
            },{
                "           ",
                "           ",
                "           ",
                "           ",
                "           ",
                "     D     ",
                "           ",
                "           ",
                "           ",
                "           ",
                "           "
            },{
                "     D     ",
                "           ",
                "           ",
                "           ",
                "     D     ",
                "D   DDD   D",
                "     D     ",
                "           ",
                "           ",
                "           ",
                "     D     "
            },{
                "    DBD    ",
                "     C     ",
                "     D     ",
                "     D     ",
                "D   DDD   D",
                "BCDDDDDDDCB",
                "D   DDD   D",
                "     D     ",
                "     D     ",
                "     C     ",
                "    DBD    "
            },{
                "     D     ",
                "           ",
                "           ",
                "           ",
                "     D     ",
                "D   DDD   D",
                "     D     ",
                "           ",
                "           ",
                "           ",
                "     D     "
            },{
                "           ",
                "           ",
                "           ",
                "           ",
                "           ",
                "     D     ",
                "           ",
                "           ",
                "           ",
                "           ",
                "           "
            },{
                "           ",
                "           ",
                "           ",
                "           ",
                "           ",
                "     D     ",
                "           ",
                "           ",
                "           ",
                "           ",
                "           "
            },{
                "           ",
                "           ",
                "           ",
                "           ",
                "           ",
                "     C     ",
                "           ",
                "           ",
                "           ",
                "           ",
                "           "
            },{
                "           ",
                "           ",
                "           ",
                "           ",
                "     D     ",
                "    DBD    ",
                "     D     ",
                "           ",
                "           ",
                "           ",
                "           "
            }}


        )
        .addElement(
            'A',
            buildHatchAdder(MTESuperpositionManipulator.class)
                .atLeast(InputBus, OutputBus, InputHatch, OutputHatch, Maintenance)
                .casingIndex(((BlockCasings10) GregTechAPI.sBlockCasings10).getTextureIndex(15))
                .dot(1)
                .buildAndChain(
                    onElementPass(MTESuperpositionManipulator::onCasingAdded, ofBlock(GregTechAPI.sBlockCasings10, 15)))
        )
        .addElement(
            'B',
            buildHatchAdder(MTESuperpositionManipulator.class)
                .atLeast(Energy)
                .casingIndex(((BlockCasings10) GregTechAPI.sBlockCasings10).getTextureIndex(15))
                .dot(2)
                .buildAndChain(
                    onElementPass(MTESuperpositionManipulator::onCasingAdded, ofBlock(GregTechAPI.sBlockCasings10, 15)))
        )
        .addElement('C', ofFrame(Materials.Steel))
        .addElement('D',
            buildHatchAdder(MTESuperpositionManipulator.class)
                .casingIndex(11)
                .buildAndChain(GregTechAPI.sBlockCasings1, 11))
        .build();

    protected MTESuperpositionManipulator(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    private int mCasingAmount;

    private void onCasingAdded() {
        mCasingAmount++;
    }

    public MTESuperpositionManipulator(String aName) {
        super(aName);
    }

    @Override
    public IStructureDefinition<MTESuperpositionManipulator> getStructureDefinition() {
        return STRUCTURE_DEFINITION;
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new MTESuperpositionManipulator(this.mName);


    }

    @Override
    public ITexture[] getTexture(IGregTechTileEntity baseMetaTileEntity, ForgeDirection side, ForgeDirection aFacing,
                                 int colorIndex, boolean aActive, boolean redstoneLevel) {
        ITexture[] rTexture;
        if (side == aFacing) {
            if (aActive) {
                rTexture = new ITexture[] {
                    Textures.BlockIcons
                        .getCasingTextureForId(GTUtility.getCasingTextureIndex(GregTechAPI.sBlockCasings10, 15)),
                    TextureFactory.builder()
                        .addIcon(OVERLAY_FRONT_MULTI_BREWERY_ACTIVE)
                        .extFacing()
                        .build(),
                    TextureFactory.builder()
                        .addIcon(OVERLAY_FRONT_MULTI_BREWERY_ACTIVE_GLOW)
                        .extFacing()
                        .glow()
                        .build() };
            } else {
                rTexture = new ITexture[] {
                    Textures.BlockIcons
                        .getCasingTextureForId(GTUtility.getCasingTextureIndex(GregTechAPI.sBlockCasings10, 15)),
                    TextureFactory.builder()
                        .addIcon(OVERLAY_FRONT_MULTI_BREWERY)
                        .extFacing()
                        .build(),
                    TextureFactory.builder()
                        .addIcon(OVERLAY_FRONT_MULTI_BREWERY_GLOW)
                        .extFacing()
                        .glow()
                        .build() };
            }
        } else {
            rTexture = new ITexture[] { Textures.BlockIcons
                .getCasingTextureForId(GTUtility.getCasingTextureIndex(GregTechAPI.sBlockCasings10, 15)) };
        }
        return rTexture;
    }

    @Override
    protected MultiblockTooltipBuilder createTooltip() {
        MultiblockTooltipBuilder tt = new MultiblockTooltipBuilder();
        tt.addMachineType("PDQSM, Pseudo-Deterministic Quantum Superposition Manipulator")
            .addInfo("Manipulates Quantum Systems and then collapses them to obtain different outputs")
            .beginStructureBlock(11, 11, 11, false)
            .addController("Front Center")
            .addCasingInfoMin("Reinforced Wooden Casing", 61, false)
            .addCasingInfoExactly("Steel Frame Box", 6, false)
            .addInputBus("Any Wooden Casing", 1)
            .addOutputBus("Any Wooden Casing", 1)
            .addInputHatch("Any Wooden Casing", 1)
            .addOutputHatch("Any Wooden Casing", 1)
            .addEnergyHatch("Any Wooden Casing", 2)
            .addEnergyHatch("Any side centered on pillars", 2)
            .addMaintenanceHatch("Any Wooden Casing", 1)

            .toolTipFinisher();

        return tt;
    }

    @Override
    public void construct(ItemStack stackSize, boolean hintsOnly) {
        buildPiece(STRUCTURE_PIECE_MAIN, stackSize, hintsOnly, 6, 6, 0);
    }

    @Override
    public int survivalConstruct(ItemStack stackSize, int elementBudget, ISurvivalBuildEnvironment env) {
        if (mMachine) return -1;
        return survivalBuildPiece(STRUCTURE_PIECE_MAIN, stackSize, 6, 6, 0, elementBudget, env, false, true);
    }

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        mCasingAmount = 0;
        return checkPiece(STRUCTURE_PIECE_MAIN, 1, 2, 0) && mCasingAmount >= 14;
    }

    @Override
    public RecipeMap<?> getRecipeMap() {
        return RecipeMaps.brewingRecipes;
    }

    @Override
    public boolean supportsVoidProtection() {
        return true;
    }

    @Override
    public boolean supportsBatchMode() {
        return true;
    }

    @Override
    public boolean supportsInputSeparation() {
        return true;
    }




}
